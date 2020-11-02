package br.com.gmfonseca.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.jump.JumpCommand
import br.com.gmfonseca.application.command.pause.PauseCommand
import br.com.gmfonseca.application.command.play.PlayCommand
import br.com.gmfonseca.application.command.queue.QueueCommand
import br.com.gmfonseca.application.command.resume.ResumeCommand
import br.com.gmfonseca.application.command.skip.SkipCommand
import br.com.gmfonseca.utils.EmbedMessage
import br.com.gmfonseca.utils.ext.equalsIgnoreCase
import br.com.gmfonseca.utils.ext.getAnnotation
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command {

    private var name: String = ""
    private val aliases = mutableListOf<String>()

    override fun toString(): String {
        return "${DiscordApp.COMMAND_PREFIX}$name"
    }

    abstract fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean

    protected fun onWrongCommand(channel: TextChannel, extra: String = "") {
        EmbedMessage.failure(
            channel = channel,
            title = "Comando inválido",
            description = "Por favor, use $this $extra"
        )
    }

    companion object {
        // Should be sorted by its priority
        private val values = listOf(
            PlayCommand(),
            QueueCommand(),
            JumpCommand(),
            SkipCommand(),
            PauseCommand(),
            ResumeCommand(),
        )

        init {
            // Allow only unique names and aliases
            val namesAliases = mutableSetOf<String>()

            values.forEach { command ->
                command.getAnnotation(CommandHandler::class)?.run {
                    if (namesAliases.add(name)) {
                        command.name = name
                    }

                    aliases.filter { it !in namesAliases }.let {
                        if (namesAliases.addAll(it)) {
                            command.aliases.addAll(it)
                        }
                    }
                }
            }
        }

        fun fromName(name: String): Command {
            return values.find { (it.name equalsIgnoreCase name) || (name.toLowerCase() in it.aliases) }
                ?: UnknownCommand
        }
    }
}
