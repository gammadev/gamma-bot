package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.music.MusicManager
import br.com.gmfonseca.bot.commandmanager.commands.BaseCommand
import br.com.gmfonseca.bot.utils.Emoji.WAVE
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "leave", aliases = ["quit"])
class LeaveCommand : BaseCommand() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guild = channel.guild

        return MusicManager.clearMusicManager(guild.id)?.let {
            it.scheduler.stop()
            guild.audioManager.closeAudioConnection()
            message.addReaction(WAVE).queue()
            true
        } ?: false
    }
}
