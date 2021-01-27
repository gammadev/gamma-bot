package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.annotations.CommandHandler
import br.com.gmfonseca.music.application.listener.TrackSchedulerListener
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "pause")
class PauseCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        super.onCommand(message, channel, args)

        val guildId = channel.guild.idLong
        val musicManager = DiscordApp.getMusicManager(guildId)

        with(musicManager.scheduler) {
            listener = TrackSchedulerListener(channel)
            pause(channel)
        }

        return true
    }
}