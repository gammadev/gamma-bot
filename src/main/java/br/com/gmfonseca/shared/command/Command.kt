package br.com.gmfonseca.shared.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.annotations.CommandHandler
import br.com.gmfonseca.shared.util.EmbedMessage
import br.com.gmfonseca.shared.util.ext.equalsIgnoreCase
import br.com.gmfonseca.shared.util.ext.getAnnotation
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command {

    private var name: String = ""
    private val aliases = mutableListOf<String>()

    override fun toString(): String {
        return "${DiscordApp.COMMAND_PREFIX}$name"
    }

    /**
     * To everything works fine, call this super method if you are not sure about what you doing.
     */
    open fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        DiscordApp.putLatestTextChannel(channel.guild.idLong, channel)

        return true
    }

    protected fun onWrongCommand(channel: TextChannel, extra: String = "") {
        EmbedMessage.failure(
            channel = channel,
            title = "Comando inválido",
            description = "Por favor, use $this $extra"
        )
    }

    companion object {
        private val commands: MutableList<Command> = mutableListOf()
        private val namesAliases = mutableSetOf<String>()

        fun fromName(name: String): Command {
            return commands.find { (it.name equalsIgnoreCase name) || (name.toLowerCase() in it.aliases) }
                ?: UnknownCommand
        }

        fun loadCommands(commands: List<Command>) {
            if (Companion.commands.isNotEmpty()) return

            commands.forEach { command ->
                command.getAnnotation(CommandHandler::class)?.run {
                    if (!enabled) return@run

                    if (namesAliases.add(name)) {
                        command.name = name
                    }

                    aliases.filter { it !in namesAliases }.let {
                        if (namesAliases.addAll(it)) {
                            command.aliases.addAll(it)
                        }
                    }

                    Companion.commands.add(command)
                }
            }
        }
    }
}
