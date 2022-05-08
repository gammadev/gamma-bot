package br.com.gmfonseca.bot.commandmanager.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
object UnknownCommand : Command {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>) = true
}
