package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.annotations.CommandHandler
import br.com.gmfonseca.shared.util.Emoji.WAVE
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "leave", aliases = ["quit"])
class LeaveCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        super.onCommand(message, channel, args)

        return DiscordApp.removeMusicManager(channel.guild.idLong)?.let {
            it.scheduler.stop()
            channel.guild.audioManager.closeAudioConnection()
            message.addReaction(WAVE).queue()
            true
        } ?: false
    }
}