package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.command.CommandHandler
import br.com.gmfonseca.music.application.listener.TrackSchedulerListener
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "queue", aliases = ["q"])
class QueueCommand : Command() {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = DiscordApp.getMusicManager(guildId)
        val scheduler = musicManager.scheduler

        with(scheduler) {
            listener = TrackSchedulerListener(channel)
            channel.sendMessage(queueToString()).queue()
        }

        return true
    }
}