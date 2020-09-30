package br.com.gmfonseca.application.listener

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.business.client.YoutubeClient
import br.com.gmfonseca.business.utils.ext.queue
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color
import kotlin.concurrent.thread

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
class YoutubeClientListener(private val channel: TextChannel) : YoutubeClient.YoutubeClientListener {

    override fun onTrackLoaded(track: AudioTrack) {
        val message = EmbedBuilder()
                .setTitle("Opa!")
                .setDescription("Adicionado ${track.info.title} à fila.")
                .setColor(Color.ORANGE)
                .build()
        channel.sendMessage(message).queue()

        play(channel.guild.id, track)
    }

    override fun onPlaylistLoaded(tracks: List<AudioTrack>) {

        if (tracks.isNotEmpty()) {
            val track = tracks.first()
            EmbedBuilder().setTitle("Opa!")
                    .setDescription("Adicionadas **${tracks.size}** músicas à fila.")
                    .setColor(Color.ORANGE)
                    .build()
                    .queue(channel)

            EmbedBuilder()
                    .setColor(Color.ORANGE)
                    .setDescription("Tocando **${track.info.title}** agora!")
                    .setFooter(track.info.author)
                    .build()
                    .queue(channel)

            thread {
                tracks.forEach {
                    play(channel.guild.id, it)
                }
            }
        } else {
            EmbedBuilder().setTitle("Ops!")
                    .setDescription("Esta playlist estava vazia.")
                    .setColor(Color.RED)
                    .build()
                    .queue(channel)
        }
    }

    override fun onNoMatches() {
        EmbedBuilder()
                .setTitle("Ops!")
                .setDescription("Não consegui encontrar nada com o que você me passou.")
                .setColor(Color.RED)
                .build()
                .queue(channel)
    }

    override fun onLoadFailed(message: String?) {
        EmbedBuilder()
                .setTitle("Ops!")
                .setDescription("Ocorreu um erro ao tentar encontrar seu pedido.")
                .setFooter("Erro: $message")
                .setColor(Color.RED)
                .build()
                .queue(channel)
    }

    private fun play(guildId: String, track: AudioTrack) {
        DiscordApp.getMusicManager(guildId).scheduler.queue(track)
    }
}