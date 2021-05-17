package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.DiscordApp
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.shared.command.Command
import br.com.gmfonseca.bot.shared.util.Emoji.WAVE
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "leave", aliases = ["quit"])
class LeaveCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guild = channel.guild

        return DiscordApp.clearMusicManager(guild.id)?.let {
            it.scheduler.stop()
            guild.audioManager.closeAudioConnection()
            message.addReaction(WAVE).queue()
            true
        } ?: false
    }
}