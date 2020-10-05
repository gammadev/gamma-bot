package br.com.gmfonseca.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.command.jump.JumpCommand
import br.com.gmfonseca.application.command.pause.PauseCommand
import br.com.gmfonseca.application.command.play.PlayCommand
import br.com.gmfonseca.application.command.queue.QueueCommand
import br.com.gmfonseca.application.command.resume.ResumeCommand
import br.com.gmfonseca.business.utils.ext.equalsIgnoreCase
import br.com.gmfonseca.business.utils.ext.queue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import java.awt.Color

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
abstract class Command(private val name: String) {

    abstract fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean

    open fun onWrongCommand(channel: TextChannel, argsTip: String? = null) {
        val extra = argsTip ?: ""
        EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Comando inválido")
                .setDescription("Por favor, use $this $extra")
                .build()
                .queue(channel)
    }

    override fun toString(): String {
        return "${DiscordApp.COMMAND_PREFIX}$name"
    }

    @Suppress("UNUSED")
    enum class Commands(private val command: Command) {
        PLAY(PlayCommand),
        PAUSE(PauseCommand),
        RESUME(ResumeCommand),
        JUMP(JumpCommand),
        QUEUE(QueueCommand),
        UNKNOWN(UnknownCommand);

        fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
            return command.onCommand(author, channel, args)
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