package br.com.gmfonseca.bot.commandmanager.ext

import br.com.gmfonseca.bot.commandmanager.Command
import br.com.gmfonseca.bot.commandmanager.CommandManager

val String.isCommand: Boolean get() = isNotBlank() && startsWith(CommandManager.COMMAND_PREFIX)

val String.asCommand: Command
    get() {
        val command = split(" ").first().substring(CommandManager.COMMAND_PREFIX.length).toLowerCase()
        return CommandManager.findCommand(command)
    }

val String.commandArgs: List<String> get() = split(" ").run { subList(1, size) }
