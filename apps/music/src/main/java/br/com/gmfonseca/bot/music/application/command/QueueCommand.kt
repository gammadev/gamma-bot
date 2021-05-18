package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.MusicManager
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.music.application.listener.TrackSchedulerListener
import br.com.gmfonseca.bot.shared.command.Command
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "queue", aliases = ["q"])
class QueueCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.id
        val musicManager = MusicManager.getMusicManager(guildId)
        val scheduler = musicManager.scheduler

        with(scheduler) {
            listener = TrackSchedulerListener(channel)
            channel.sendMessage(queueToString()).queue()
        }

        return true
    }
}