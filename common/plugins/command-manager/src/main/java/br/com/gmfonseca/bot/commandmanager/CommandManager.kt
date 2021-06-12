package br.com.gmfonseca.bot.commandmanager

import br.com.gmfonseca.bot.commandmanager.handler.message.GuildMessageHandler
import br.com.gmfonseca.bot.core.discord.managers.PluginManager
import net.dv8tion.jda.api.JDA
import java.util.logging.Level
import java.util.logging.Logger

object CommandManager : PluginManager {

    const val COMMAND_PREFIX = ">"

    val COMMANDS = mutableMapOf<String, Command>()
    private val logger = Logger.getLogger("CommandManager")

    override fun init(jda: JDA): Boolean = run {
        jda.addEventListener(GuildMessageHandler())
        true
    }

    fun registerCommands(commands: Iterable<Command>) = commands.forEach { command ->
        val existentCommand = COMMANDS.putIfAbsent(command.name, command)
        val commandClassName = command::class.java.name

        if (existentCommand != null) {
            logger.log(
                Level.WARNING,
                registrationFailureMessage(commandClassName, "Name '${command.name}'", existentCommand::class.java.name)
            )
        }

        command.aliases.forEach { alias ->
            val existentAlias = COMMANDS.putIfAbsent(alias, command)

            if (existentAlias != null) {
                logger.log(
                    Level.WARNING,
                    registrationFailureMessage(commandClassName, "Alias '$alias'", existentAlias::class.java.name)
                )
            }
        }
    }

    fun findCommand(name: String): Command {
        return COMMANDS[name] ?: UnknownCommand
    }

    private fun registrationFailureMessage(
        className: String,
        previouslyRegisteredReason: String,
        registerOwner: String
    ): String {
        return "Failed to register command '%s'. %s was previously registered by %s".format(
            className, previouslyRegisteredReason, registerOwner
        )
    }
}
