package br.com.gmfonseca.bot.music.data.clients.youtube

import br.com.gmfonseca.bot.music.MusicManager
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeSearchProvider
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import kotlin.concurrent.thread

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class YoutubeClientImpl(private val listener: YoutubeClientListener): YoutubeClient {

    override fun search(identifier: String) {
        YoutubeSearchProvider().loadSearchResult(identifier) { trackInfo ->
            trackInfo?.run {
                listener.onTrackLoaded(YoutubeAudioTrack(trackInfo, YoutubeAudioSourceManager()))
            } ?: listener.onNoMatches()
            null
        }
    }

    override fun download(identifier: String) {
        thread {
            MusicManager.PLAYER_MANAGER.loadItem(identifier, object : AudioLoadResultHandler {
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
    }

    interface YoutubeClientListener {

        fun onTrackLoaded(track: AudioTrack)

        fun onPlaylistLoaded(tracks: List<AudioTrack>)

        fun onNoMatches()

        fun onLoadFailed(message: String?)
    }
}
