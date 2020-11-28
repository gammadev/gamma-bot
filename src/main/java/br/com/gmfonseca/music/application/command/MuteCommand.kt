package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.listener.TrackSchedulerListener
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.command.CommandHandler
import br.com.gmfonseca.shared.util.EmbedMessage
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "mute", aliases = ["m"])
class MuteCommand : Command() {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val scheduler = DiscordApp.getMusicManager(channel.guild.id).scheduler.apply {
            listener = TrackSchedulerListener(channel)
        }

        scheduler.switchVolume(0)

        EmbedMessage.success(channel, description = "O áudio agora está mutado") {
            val guildId = channel.guild.idLong
            LAST_MESSAGE_ID[guildId]?.let { msgId ->
                channel.deleteMessageById(msgId).queue()
            }
            LAST_MESSAGE_ID[guildId] = it.idLong
        }

        return true
    }

    companion object {
        private val LAST_MESSAGE_ID = mutableMapOf<Long, Long>()
    }

}