package br.com.gmfonseca.business.utils.ext

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.business.command.Command

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */

fun String.isCommand(): Boolean {
    return isNotBlank() && first() == DiscordApp.COMMAND_PREFIX
}

fun String.getCommand(): Command.Commands {
    return Command.Commands.fromName(substring(1).trim())
}

fun String.getCommandArgs(): List<String> {
    return split(" ").run { toMutableList().subList(1, size) }
}

infix fun String.equalsIgnoreCase(other: String): Boolean {
    return toLowerCase() == other.toLowerCase()
}