package br.com.gmfonseca.application.command.jump

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.Command
import br.com.gmfonseca.application.listener.TrackSchedulerListener
import br.com.gmfonseca.business.utils.EmbedMessage
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 03/10/2020.
 */
object JumpCommand : Command("jump") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = DiscordApp.getMusicManager(guildId)
        val scheduler = musicManager.scheduler

        with(scheduler) {
            listener = TrackSchedulerListener(channel)
        }

        if (args.isEmpty()) {
            scheduler.skip()
        } else {
            val guild = channel.guild
            val voiceChannel = guild.voiceChannels.find { voiceChannel ->
                voiceChannel.members.find { it.user.idLong == author.idLong } != null
            }

            if (voiceChannel == null) {
                EmbedMessage.failure(
                        channel,
                        description = "Você não está conectado em um canal de voz!"
                )
            } else {
                // try to connect to voice channel if not connected before or user changed voice channel
                musicManager.run {
                    if (this.voiceChannel != voiceChannel) {
                        this.voiceChannel = voiceChannel

                        with(guild.audioManager) {
                            sendingHandler = musicManager.audioSenderHandler
                            openAudioConnection(voiceChannel) // TODO: threat InsufficientPermissionException here
                        }
                    }
                }

                // schedule jump music
                args.first().toIntOrNull()?.let {
                    scheduler.jump(it - 1)
                } ?: onWrongCommand(channel, "")

            }
        }

        return true
    }
}