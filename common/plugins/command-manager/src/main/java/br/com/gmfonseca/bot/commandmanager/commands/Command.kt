package br.com.gmfonseca.bot.commandmanager.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

interface Command {
    fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean
}