package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.music.MusicManager
import br.com.gmfonseca.bot.commandmanager.commands.BaseCommand
import br.com.gmfonseca.bot.music.application.listener.TrackSchedulerListener
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 29/10/2020.
 */
@CommandHandler(name = "skip", aliases = ["s", "next", "n"])
class SkipCommand : BaseCommand() {
    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        with(MusicManager.getMusicManager(channel.guild.id).scheduler) {
            listener = TrackSchedulerListener(channel)
            skip()
        }

        return true
    }
}
