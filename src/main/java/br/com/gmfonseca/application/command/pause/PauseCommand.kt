package br.com.gmfonseca.application.command.pause

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.Command
import br.com.gmfonseca.application.listener.TrackSchedulerListener
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
object PauseCommand : Command("pause") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = DiscordApp.getMusicManager(guildId)
        val scheduler = musicManager.scheduler

        with(scheduler) {
            listener = TrackSchedulerListener(channel)
        }

        scheduler.pause(channel)

        return true
    }
}