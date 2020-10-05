package br.com.gmfonseca.application.handler.audio

import br.com.gmfonseca.business.utils.EmbedMessage
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import com.sedmelluq.discord.lavaplayer.track.BaseAudioTrack
import com.sedmelluq.discord.lavaplayer.track.TrackStateListener
import net.dv8tion.jda.api.entities.TextChannel
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.floor

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

    fun resume(channel: TextChannel) { // TODO: The resume isn't playing from last state, but restarting the music
        synchronized(this) {
            val track = curTrack?.let { it as BaseAudioTrack }
            track?.activeExecutor?.execute(object : TrackStateListener {
                override fun onTrackException(p0: AudioTrack?, p1: FriendlyException?) {
                    EmbedMessage.failure(channel, description = "Ocorreu um erro ao iniciar: ${p1?.message}")
                }

                override fun onTrackStuck(p0: AudioTrack?, p1: Long) {
                    EmbedMessage.failure(channel, description = "O audio esta engargalando.")
                }
            })
        }
    }

    fun stop() {

    }

    fun pause(channel: TextChannel) { // TODO: check if this method is pausing the audio correctly
        synchronized(this) {
            curTrack?.let {
                EmbedMessage.success(channel, description = "Parando de tocar agora!")
                it.stop()
            }

        }
    }

    fun queueToString(): String { // TODO: This method should return less than 2000 total characters
        val strBuilder = StringBuilder()

        synchronized(this) {
            val curIndex = this.curIndex
            val curTrack = this.curTrack
            val queue = this.trackQueue

            if (queue.isEmpty()) {
                strBuilder.append("A lista está vazia :cry:")
            } else {
                val format = DecimalFormat("00")

                strBuilder.append("```python\n")

                queue.forEachIndexed { i, track ->
                    if (i == curIndex) {
                        val printTrack = curTrack ?: track

                        strBuilder.append(" \\/ audio atual \n")
                        strBuilder.append("[${curIndex + 1}] ${track.info.title} ${format.format(printTrack.duration / 60_000)}:${format.format(abs(floor(printTrack.duration / 60_000.0) - (printTrack.duration / 60_000.0)) * 60)} \n")
                        strBuilder.append(" /\\ audio atual \n")
                    } else {
                        strBuilder.append("[${i + 1}] ${track.info.title} -- ${format.format(track.duration / 60_000)}:${format.format(abs(floor(track.duration / 60_000.0) - (track.duration / 60_000.0)) * 60)} \n")
                    }
                }
            }

            strBuilder.append("```")
        }

        return strBuilder.toString()
    }

    fun queue(track: AudioTrack) {
        synchronized(this) {
            trackQueue.add(track)
            if (player.startTrack(track, true)) {
                curIndex = trackQueue.indexOf(track)
                curTrack = track
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
        playTrackAt(curIndex + 1)
    }

    private fun playTrackAt(index: Int) {
        synchronized(this) {
            if (!trackQueue.indices.contains(index)) {
                listener?.onWrongIndex(index)
                return
            }

            curIndex = index

            trackQueue[index].makeClone().let {
                curTrack = it
                player.startTrack(it, false)
                listener?.onNextTrack(it)
            }
        }
    }

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        if (endReason.mayStartNext) {
            nextTrack()
        }
    }

    interface ITrackSchedulerListener {

        fun onNextTrack(track: AudioTrack)

        fun onWrongIndex(index: Int)

    }
}