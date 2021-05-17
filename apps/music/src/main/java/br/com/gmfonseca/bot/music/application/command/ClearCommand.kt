package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.DiscordApp
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.music.application.listener.TrackSchedulerListener
import br.com.gmfonseca.bot.shared.command.Command
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "clear", aliases = ["c"])
class ClearCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = DiscordApp.getMusicManager(guildId)

        with(musicManager.scheduler) {
            listener = TrackSchedulerListener(channel)
            clearQueue(channel)
        }

        return true
    }
}