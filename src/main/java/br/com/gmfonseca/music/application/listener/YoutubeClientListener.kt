package br.com.gmfonseca.music.application.listener

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.business.client.YoutubeClient
import br.com.gmfonseca.shared.util.EmbedMessage
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel
import java.util.concurrent.CompletableFuture

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

        playTrack(channel.guild.idLong, track)
    }

    override fun onPlaylistLoaded(tracks: List<AudioTrack>) {
        if (tracks.isNotEmpty()) {
            EmbedMessage.success(
                channel,
                title = "Opa!",
                description = "Adicionadas **${tracks.size}** músicas à fila."
            )

            playTracksAsync(channel.guild.idLong, tracks)
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

    private fun playTracksAsync(guildId: Long, tracks: List<AudioTrack>) {
        CompletableFuture.runAsync {
            tracks.forEach { playTrack(guildId, it) }
        }
    }

    private fun playTrack(guildId: Long, track: AudioTrack) {
        DiscordApp.getMusicManager(guildId).scheduler.queue(track)
    }

}
