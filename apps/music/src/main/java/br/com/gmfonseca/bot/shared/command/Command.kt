package br.com.gmfonseca.bot.shared.command

import br.com.gmfonseca.bot.DiscordApp
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.generated.Statics
import br.com.gmfonseca.bot.shared.exceptions.IllegalCommandClassException
import br.com.gmfonseca.bot.shared.util.EmbedMessage
import br.com.gmfonseca.bot.shared.util.ext.getAnnotation
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command {

    val name: String
    private val aliases: List<String>

    init {
        val handlerAnn = getAnnotation(CommandHandler::class)

        requireNotNull(handlerAnn) { throw IllegalCommandClassException(this::class) }

        name = handlerAnn.name.toLowerCase()
        aliases = handlerAnn.aliases.map { it.toLowerCase() }
    }

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
        fun findCommand(name: String): Command {
            return Statics.COMMANDS.find { it.name == name || name in it.aliases } ?: UnknownCommand
        }
    }

}
