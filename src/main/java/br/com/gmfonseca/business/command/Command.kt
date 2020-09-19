package br.com.gmfonseca.business.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.business.command.play.PlayCommand
import br.com.gmfonseca.business.utils.ext.createInstance
import br.com.gmfonseca.business.utils.ext.equalsIgnoreCase
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import kotlin.reflect.KClass

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command(private val name: String) {

    abstract fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean

    override fun toString(): String {
        return "${DiscordApp.COMMAND_PREFIX}$name"
    }

    @Suppress("UNUSED")
    enum class Commands(private val klass: KClass<out Command>) {
        PLAY(PlayCommand::class),
        UNKNOWN(UnknownCommand::class);

        fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
            return getCommandInstance().onCommand(author, channel, args)
        }

        private fun getCommandInstance(): Command {
            return klass.createInstance()
        }

        override fun toString(): String {
            return name.toLowerCase()
        }

        companion object {
            fun fromName(name: String): Commands {
                return values().find { it.name equalsIgnoreCase name } ?: UNKNOWN
            }
        }
    }
}