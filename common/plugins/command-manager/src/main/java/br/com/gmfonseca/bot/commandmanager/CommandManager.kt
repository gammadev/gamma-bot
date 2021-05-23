package br.com.gmfonseca.bot.commandmanager

import java.util.logging.Level
import java.util.logging.Logger

object CommandManager {

    const val COMMAND_PREFIX = '>'

    private val COMMANDS = mutableMapOf<String, Command>()
    private val logger = Logger.getLogger("CommandManager")

    fun registerCommands(commands: Iterable<Command>) = commands.forEach { command ->
        val existentCommand = COMMANDS.putIfAbsent(command.name, command)

        if (existentCommand != null) {
            logger.log(
                Level.WARNING,
                "Failed to register command '${command::class.java.name}'. Name '${command.name}' was previously registered by ${existentCommand::class.java.name}"
            )
        }

        command.aliases.forEach { alias ->
            val existentAlias = COMMANDS.putIfAbsent(alias, command)

            if (existentAlias != null) {
                logger.log(
                    Level.WARNING,
                    "Failed to register command '${command::class.java.name}'. Alias '${alias}' was previously registered by ${existentAlias::class.java.name}"
                )
            }
        }
    }

    fun findCommand(name: String): Command {
        return COMMANDS[name] ?: UnknownCommand
    }
}