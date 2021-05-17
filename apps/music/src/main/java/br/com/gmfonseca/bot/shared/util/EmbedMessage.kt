package br.com.gmfonseca.bot.shared.util

import br.com.gmfonseca.bot.shared.util.ext.queue
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color

/**
 * Created by Gabriel Fonseca on 02/10/2020.
 */
object EmbedMessage {
    private val SUCCESS = Color.ORANGE
    private val INFO = Color.CYAN
    private val ERROR = Color.RED

    fun success(
            channel: TextChannel,
            title: String? = null,
            description: String? = null,
            footer: String? = null,
            onMessageSent: (Message) -> Unit = {}
    ) {
        buildAndQueue(
                channel,
                color = SUCCESS,
                title = title,
                description = description,
                footer = footer,
                onMessageSent = onMessageSent
        )
    }

    fun info(
            channel: TextChannel,
            title: String? = null,
            description: String? = null,
            onMessageSent: (Message) -> Unit = {}
    ) {
        buildAndQueue(
                channel,
                color = INFO,
                title = title,
                description = description,
                onMessageSent = onMessageSent
        )
    }

    fun failure(
            channel: TextChannel,
            title: String = "Ops!",
            description: String? = null,
            footer: String? = null,
            onMessageSent: (Message) -> Unit = {}
    ) {
        buildAndQueue(
                channel,
                color = ERROR,
                title = title,
                description = description,
                footer = footer,
                onMessageSent = onMessageSent
        )
    }

    private fun buildAndQueue(
            channel: TextChannel,
            color: Color? = null,
            title: String? = null,
            description: String? = null,
            footer: String? = null,
            onMessageSent: (Message) -> Unit = {}
    ) {
        EmbedBuilder().apply {

            color?.let { setColor(color) }
            title?.let { setTitle(it) }
            description?.let { setDescription(it) }
            footer?.let { setFooter(it) }

        }.build().queue(channel, onMessageSent)
    }

}