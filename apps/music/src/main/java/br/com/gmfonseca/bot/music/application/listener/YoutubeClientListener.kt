package br.com.gmfonseca.bot.music.application.listener

import br.com.gmfonseca.bot.core.discord.EmbedMessage
import br.com.gmfonseca.bot.music.MusicManager
import br.com.gmfonseca.bot.music.data.clients.youtube.YoutubeClientImpl
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel
import kotlin.concurrent.thread

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
class YoutubeClientListener(private val channel: TextChannel) : YoutubeClientImpl.YoutubeClientListener {

    override fun onTrackLoaded(track: AudioTrack) {
        EmbedMessage.success(
            channel,
            title = "Opa!",
            description = "Adicionado ${track.info.title} à fila."
        )

        play(channel.guild.id, track)
    }

    override fun onPlaylistLoaded(tracks: List<AudioTrack>) {
        if (tracks.isNotEmpty()) {
            EmbedMessage.success(
                channel,
                title = "Opa!",
                description = "Adicionadas **${tracks.size}** músicas à fila."
            )

            thread {
                tracks.forEach {
                    play(channel.guild.id, it)
                }
            }
        } else {
            EmbedMessage.failure(
                channel,
                description = "Esta playlist estava vazia."
            )
        }
    }

    override fun onNoMatches() {
        EmbedMessage.failure(
            channel,
            description = "Não consegui encontrar nada com o que você me passou."
        )
    }

    override fun onLoadFailed(message: String?) {
        EmbedMessage.failure(
            channel,
            description = "Ocorreu um erro ao tentar encontrar seu pedido.",
            footer = "Erro: $message"
        )
    }

    private fun play(guildId: String, track: AudioTrack) {
        MusicManager.getMusicManager(guildId).scheduler.queue(track)
    }
}
