package br.com.gmfonseca.application.handler.audio

import br.com.gmfonseca.utils.EmbedMessage
import br.com.gmfonseca.utils.ext.fill
import br.com.gmfonseca.utils.ext.msToReadableTime
import br.com.gmfonseca.utils.ext.truncateOrFill
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

    var listener: ITrackSchedulerListener? = null
        set(value) {
            synchronized(this) {
                field = value
            }
        }

    private val trackQueue = mutableListOf<AudioTrack>()
    private var curIndex: Int = -1
    private var curTrack: AudioTrack? = null

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        if (endReason.mayStartNext) {
            nextTrack(shouldNotify = endReason != AudioTrackEndReason.LOAD_FAILED)
        }
    }

    fun resume(channel: TextChannel) {
        synchronized(this) {
            EmbedMessage.success(channel, description = "Voltando a tocar agora!")
            player.isPaused = false
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

    fun queueToString(): String = synchronized(this) {
        val strBuilder = StringBuilder("```swift\n")
        val curIndex = this.curIndex
        val queue = this.trackQueue

        if (queue.isEmpty()) {
            strBuilder.append("A lista está vazia \uD83E\uDD14")
        } else {
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
        }

        return strBuilder.append("```").toString()
    }

    fun queue(track: AudioTrack) {
        synchronized(this) {
            trackQueue.add(track)
            if (player.startTrack(track, true)) {
                curIndex = trackQueue.indexOf(track)
                curTrack = track
                listener?.onNextTrack(track)
            }
        }
    }

    fun jump(index: Int) {
        playTrackAt(index)
    }

    fun skip() {
        nextTrack()
    }

    private fun nextTrack(shouldNotify: Boolean = true) {
        playTrackAt(curIndex + 1, shouldNotify = shouldNotify)
    }

    private fun playTrackAt(index: Int, shouldNotify: Boolean = true) {
        synchronized(this) {
            if (!trackQueue.indices.contains(index)) {
                listener?.onWrongIndex(index)
                return
            }

            if (player.isPaused) {
                player.isPaused = false
            }

            curIndex = index

            trackQueue[index].makeClone().let {
                curTrack = it
                player.startTrack(it, false)
                if (shouldNotify) {
                    listener?.onNextTrack(it)
                }
            }
        }
    }

    interface ITrackSchedulerListener {

        fun onNextTrack(track: AudioTrack)

        fun onWrongIndex(index: Int)

    }
}