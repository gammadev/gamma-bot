package br.com.gmfonseca.bot.commandmanager

import br.com.gmfonseca.bot.commandmanager.commands.BaseCommand
import br.com.gmfonseca.bot.commandmanager.commands.Command
import br.com.gmfonseca.bot.commandmanager.commands.UnknownCommand
import br.com.gmfonseca.bot.commandmanager.handler.message.GuildMessageHandler
import br.com.gmfonseca.bot.core.Logger.warn
import br.com.gmfonseca.bot.core.discord.managers.PluginManager
import net.dv8tion.jda.api.JDA
import kotlin.reflect.jvm.jvmName

object CommandManager : PluginManager {

    const val COMMAND_PREFIX = ">"

    private val commands = mutableMapOf<String, Command>()

    override fun init(jda: JDA): Boolean {
        jda.addEventListener(GuildMessageHandler())
        return true
    }

    fun findCommand(name: String): Command = commands[name] ?: UnknownCommand

    fun registerCommands(commands: Iterable<Command>) {
        commands.forEach { command ->
            if (command is BaseCommand) command.register()
        }
    }

    private fun BaseCommand.register() {
        val commandClassName = this::class.jvmName

        registerForName(name, commandClassName)
        registerForNames(aliases, commandClassName)
    }

    private fun Command.registerForNames(names: List<String>, commandClassName: String) {
        names.forEach { name -> registerForName(name, commandClassName) }
    }

    private fun Command.registerForName(name: String, commandClassName: String) {
        val currentNameOwner = commands.putIfAbsent(name, this)

        if (currentNameOwner != null) {
            warn(COMMAND_REGISTRATION_FAILURE_MESSAGE.format(commandClassName, name, currentNameOwner::class.jvmName))
        }
    }

    private const val COMMAND_REGISTRATION_FAILURE_MESSAGE =
        "Failed to register the command '%1\$s' for the name '%2\$s', which was previously registered by '%3\$s'"
}
