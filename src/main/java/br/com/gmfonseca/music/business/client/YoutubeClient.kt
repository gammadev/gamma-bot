package br.com.gmfonseca.music.business.client

import br.com.gmfonseca.DiscordApp
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeSearchProvider
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import java.util.concurrent.CompletableFuture


/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class YoutubeClient(private val listener: YoutubeClientListener) {

    fun search(identifier: String) {
        YoutubeSearchProvider().loadSearchResult(identifier) { trackInfo ->
            trackInfo?.run {
                listener.onTrackLoaded(YoutubeAudioTrack(trackInfo, YoutubeAudioSourceManager()))
            } ?: listener.onNoMatches()
            null
        }
    }

    fun downloadTrackAsync(identifier: String) {
        CompletableFuture.runAsync { downloadTrack(identifier) }
    }

    private fun downloadTrack(identifier: String) {
        DiscordApp.PLAYER_MANAGER.loadItem(identifier, object : AudioLoadResultHandler {
            override fun trackLoaded(track: AudioTrack) {
                listener.onTrackLoaded(track)
            }

            override fun playlistLoaded(playlist: AudioPlaylist) {
                listener.onPlaylistLoaded(playlist.tracks)
            }

            override fun noMatches() {
                listener.onNoMatches()
            }

            override fun loadFailed(p0: FriendlyException?) {
                listener.onLoadFailed(p0?.message)
            }
        })
    }

    interface YoutubeClientListener {

        fun onTrackLoaded(track: AudioTrack)

        fun onPlaylistLoaded(tracks: List<AudioTrack>)

        fun onNoMatches()

        fun onLoadFailed(message: String?)
    }
}