package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.music.application.listener.TrackSchedulerListener
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "stop")
class StopCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = DiscordApp.getMusicManager(guildId)

        with(musicManager.scheduler) {
            listener = TrackSchedulerListener(channel)
            stop(channel)
        }

        return true
    }
}