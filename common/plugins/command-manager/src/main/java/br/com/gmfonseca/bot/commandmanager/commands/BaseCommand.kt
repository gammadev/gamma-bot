package br.com.gmfonseca.bot.commandmanager.commands

import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.annotations.ext.getAnnotation
import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.commandmanager.exceptions.IllegalCommandClassException
import br.com.gmfonseca.bot.core.discord.EmbedMessage
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class BaseCommand : Command {

    val name: String
    val aliases: List<String>

    init {
        val handlerAnn = getAnnotation(CommandHandler::class)

        requireNotNull(handlerAnn) { throw IllegalCommandClassException(this::class) }

        name = handlerAnn.name.toLowerCase()
        aliases = handlerAnn.aliases.map(String::toLowerCase).distinct()
    }

    override fun toString() = "${CommandManager.COMMAND_PREFIX}$name"

    protected fun onWrongCommand(channel: TextChannel, extra: String = "") {
        EmbedMessage.failure(
            channel = channel,
            title = "Comando inválido",
            description = "Por favor, use $this $extra"
        )
    }
}
