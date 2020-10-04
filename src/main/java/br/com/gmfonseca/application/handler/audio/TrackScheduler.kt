package br.com.gmfonseca.application.handler.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason

/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
class TrackScheduler(
        private val player: AudioPlayer
) : AudioEventAdapter() {

    var listener: ITrackSchedulerListener? = null

    private val trackQueue = mutableListOf<AudioTrack>()
    private var curIndex: Int = -1
    private var curTrack: AudioTrack? = null

    fun queue(track: AudioTrack) {
        synchronized(trackQueue) {
            if (!player.startTrack(track, true)) {
                trackQueue.add(track)
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