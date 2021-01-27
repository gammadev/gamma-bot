package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.shared.annotations.CommandHandler
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.util.ext.connectVoice
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 19/12/2020.
 */
@CommandHandler("join")
class JoinCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        super.onCommand(message, channel, args)

        val guild = channel.guild
        val voiceChannel = guild.voiceChannels.find { voiceChannel ->
            voiceChannel.members.find { it.user.idLong == message.author.idLong } != null
        } ?: return false

        message.delete().queue()
        guild.audioManager.connectVoice(channel, voiceChannel)

        return true
    }
}