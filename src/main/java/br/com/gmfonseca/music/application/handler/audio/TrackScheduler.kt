package br.com.gmfonseca.music.application.handler.audio

import br.com.gmfonseca.shared.utils.EmbedMessage
import br.com.gmfonseca.shared.utils.ext.addIfAbsent
import br.com.gmfonseca.shared.utils.ext.fill
import br.com.gmfonseca.shared.utils.ext.msToReadableTime
import br.com.gmfonseca.shared.utils.ext.truncateOrFill
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import net.dv8tion.jda.api.entities.TextChannel
import kotlin.math.min

/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
class TrackScheduler(
    private val player: AudioPlayer
) : AudioEventAdapter() {

    val volume: Int; get() = player.volume
    var listener: ITrackSchedulerListener? = null
        set(value) = synchronized(this) {
            field = value
        }

    private val tracks = mutableListOf<AudioTrack>()
    private var curIndex: Int = -1
    private var curTrack: AudioTrack? = null
    private var isStopped: Boolean = false

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        if (endReason.mayStartNext) {
            nextTrack()
        }
    }

    fun clearQueue(channel: TextChannel) {
        synchronized(this) {
            player.stopTrack()
            player.isPaused
            curTrack?.stop()
            curTrack = null
            tracks.clear()
            EmbedMessage.success(channel, description = "Lista de reprodução limpa com sucesso!")
        }
    }

    fun switchVolume(volume: Int): Boolean {
        return if (volume in 0..1000) {
            player.volume = volume
            true
        } else {
            false
        }
    }

    fun queue(track: AudioTrack) {
        synchronized(this) {
            tracks.addIfAbsent(track)
            if (player.startTrack(track, true)) {
                curIndex = tracks.indexOf(track)
                curTrack = track
                listener?.onNextTrack(track)
            }
        }
    }

    fun queueToString(): String = synchronized(this) {
        val strBuilder = StringBuilder("```swift\n")
        val curIndex = this.curIndex
        val queue = this.tracks

        if (queue.isNotEmpty()) {
            val starterIndex = if (curIndex < 2) 0 else curIndex - 1
            val finalIndex = min(starterIndex + 10, queue.size)
            val sizeCharCount = "${queue.size}".length

            queue.subList(starterIndex, finalIndex).forEachIndexed { i, track ->
                val adjustedIndex = starterIndex + i + 1
                val displayPosition = "$adjustedIndex".fill(sizeCharCount, fillStart = true)
                val readableTime = track.duration.msToReadableTime()
                val trackTitle = track.info.title.truncateOrFill(36)

                if (starterIndex + i == curIndex) {
                    strBuilder.appendLine()
                        .append("$displayPosition | ", trackTitle, " | ", "$readableTime -- Tocando")
                        .appendLine()
                } else {
                    strBuilder.append("$displayPosition | ", trackTitle, " | ", readableTime)
                }
                strBuilder.appendLine()
            }

            if (finalIndex == queue.size) {
                strBuilder.append("\nFim da lista")
            } else {
                strBuilder.append("\nMais ${queue.size - finalIndex} abaixo")
            }
        } else {
            strBuilder.append("A lista está vazia \uD83E\uDD14")
        }

        return strBuilder.append("```").toString()
    }

    fun resume(channel: TextChannel) {
        synchronized(this) {
            when {
                player.isPaused -> {
                    player.isPaused = false
                    EmbedMessage.success(channel, description = "Voltando a tocar agora!")
                }
                isStopped -> {
                    isStopped = false
                    playTrackAt(curIndex)
                }
            }
        }
    }

    fun pause(channel: TextChannel) {
        synchronized(this) {
            curTrack?.let {
                EmbedMessage.success(channel, description = "Parando de tocar agora!")
                player.isPaused = true
            }
        }
    }

    fun stop(channel: TextChannel) {
        synchronized(this) {
            curTrack?.let {
                if (!isStopped) {
                    EmbedMessage.success(channel, description = "Parando de tocar...")
                    isStopped = true
                    curTrack?.stop()
                    player.stopTrack()
                }
            }
        }
    }

    fun jump(index: Int) {
        playTrackAt(index)
    }

    fun skip() {
        nextTrack()
    }

    private fun nextTrack() {
        val nextIndex = curIndex + 1
        if (nextIndex in tracks.indices) {
            playTrackAt(nextIndex)
        } else {
            curTrack = null
            listener?.onFinish()
        }
    }

    private fun playTrackAt(index: Int) {
        synchronized(this) {
            if (index !in tracks.indices) {
                listener?.onWrongIndex(index)
                return
            }

            if (player.isPaused) {
                player.isPaused = false
            }

            if (isStopped) {
                isStopped = false
            }

            curIndex = index

            tracks[index].makeClone().let {
                curTrack = it
                player.startTrack(it, false)
                listener?.onNextTrack(it)
            }
        }
    }

    interface ITrackSchedulerListener {

        fun onNextTrack(track: AudioTrack)

        fun onWrongIndex(index: Int)

        fun onFinish()

    }
}