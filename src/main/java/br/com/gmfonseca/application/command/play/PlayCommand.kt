package br.com.gmfonseca.application.command.play

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.Command
import br.com.gmfonseca.application.listener.TrackSchedulerListener
import br.com.gmfonseca.application.listener.YoutubeClientListener
import br.com.gmfonseca.business.client.YoutubeClient
import br.com.gmfonseca.business.utils.ext.queue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import java.awt.Color

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class PlayCommand : Command("play") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        if (args.isEmpty()) {
            onWrongCommand(channel, "")
        } else {
            val guild = channel.guild
            val voiceChannel = guild.voiceChannels.find { voiceChannel ->
                voiceChannel.members.find { it.user.idLong == author.idLong } != null
            }

            if (voiceChannel == null) {
                EmbedBuilder()
                        .setColor(Color.RED)
                        .setDescription("Você não está conectado em um canal de voz!")
                        .build()
                        .queue(channel)
            } else {
                if (args.first().matches(Regex("(http|https)://(((www|music).youtube.com/(watch|playlist)\\?[a-zA-Z0-9_\\-&=.]+)|youtu.be/[a-zA-Z0-9_\\-&=.]+)"))) {
                    YoutubeClient(YoutubeClientListener(channel)).download(args.first())
                } else {
//                    YoutubeClient(YoutubeClientListener(channel)).search(args.reduce { acc, cur -> "$acc $cur" })
                    EmbedBuilder()
                            .setTitle("Ainda não suportado!")
                            .setColor(Color.CYAN)
                            .setDescription("Ainda não suportamos pesquisas, então por favor insira uma URL válida do Youtube.")
                            .build()
                            .queue(channel)
                }

                with(guild.audioManager) {
                    val musicManager = DiscordApp.getMusicManager(guild.id).apply {
                        scheduler.listener = TrackSchedulerListener(channel)
                    }

                    sendingHandler = musicManager.audioSenderHandler
                    openAudioConnection(voiceChannel) // TODO: threat InsufficientPermissionException here
                }
            }
        }

        return true
    }
}