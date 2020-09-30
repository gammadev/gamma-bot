package br.com.gmfonseca.business.utils.ext

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.Command

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */

fun String.isCommand(): Boolean {
    return isNotBlank() && first() == DiscordApp.COMMAND_PREFIX
}

fun String.getCommand(): Command.Commands {
    val command = split(" ").first().substring(1)
    return Command.Commands.fromName(command)
}

fun String.getCommandArgs(): List<String> {
    return split(" ").run { toMutableList().subList(1, size) }
}

infix fun String.equalsIgnoreCase(other: String): Boolean {
    return equals(other, ignoreCase = true)
}