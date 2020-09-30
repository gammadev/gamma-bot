package br.com.gmfonseca.application.handler.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
class TrackScheduler(
        private val player: AudioPlayer
) : AudioEventAdapter() {
    private val trackQueue = LinkedBlockingQueue<AudioTrack>()

    var listener: ITrackSchedulerListener? = null

    fun queue(track: AudioTrack) {
        if (!player.startTrack(track, true)) {
            trackQueue.offer(track)
        }
    }

    private fun nextTrack() {
        val nextTrack = trackQueue.poll() ?: return

        player.startTrack(nextTrack, false)
        listener?.onNextTrack(nextTrack)
    }

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        if (endReason.mayStartNext) {
            nextTrack()
        }
    }

    interface ITrackSchedulerListener {

        fun onNextTrack(track: AudioTrack)

    }
}