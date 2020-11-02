package br.com.gmfonseca.application.listener

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.business.client.YoutubeClient
import br.com.gmfonseca.utils.EmbedMessage
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel
import kotlin.concurrent.thread

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
class YoutubeClientListener(private val channel: TextChannel) : YoutubeClient.YoutubeClientListener {

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
            val track = tracks.first()
            EmbedMessage.success(
                channel,
                title = "Opa!",
                description = "Adicionadas **${tracks.size}** músicas à fila."
            )

            EmbedMessage.success(
                channel,
                description = "Tocando **${track.info.title}** agora!"
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
        DiscordApp.getMusicManager(guildId).scheduler.queue(track)
    }
}