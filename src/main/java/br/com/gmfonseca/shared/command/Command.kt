package br.com.gmfonseca.shared.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.util.EmbedMessage
import br.com.gmfonseca.shared.util.ext.equalsIgnoreCase
import br.com.gmfonseca.shared.util.ext.getAnnotation
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command {

    var name: String = ""; private set
    private val aliases = mutableListOf<String>()

    override fun toString(): String {
        return "${DiscordApp.COMMAND_PREFIX}$name"
    }

    abstract fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean

    protected fun onWrongCommand(channel: TextChannel, extra: String = "") {
        EmbedMessage.failure(
            channel = channel,
            title = "Comando inválido",
            description = "Por favor, use $this $extra"
        )
    }

    companion object {
        private val mutableCommands: MutableList<Command> = mutableListOf()
        val commands: List<Command>; get() = mutableCommands

        private val namesAliases = mutableSetOf<String>()

        fun fromName(name: String): Command {
            return commands.find { (it.name equalsIgnoreCase name) || (name.toLowerCase() in it.aliases) }
                ?: UnknownCommand
        }

        fun loadCommands(commands: List<Command>) {
            if (mutableCommands.isNotEmpty()) return

            commands.forEach { command ->
                command.getAnnotation(CommandHandler::class)?.run {
                    if (namesAliases.add(name)) {
                        command.name = name
                    }

                    aliases.filter { it !in namesAliases }.let {
                        if (namesAliases.addAll(it)) {
                            command.aliases.addAll(it)
                        }
                    }

                    mutableCommands.add(command)
                }
            }
        }
    }
}
