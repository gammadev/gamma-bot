package br.com.gmfonseca.business.utils

import br.com.gmfonseca.business.utils.ext.queue
import net.dv8tion.jda.api.EmbedBuilder
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
            footer: String? = null
    ) {
        buildAndQueue(
                channel,
                color = SUCCESS,
                title = title,
                description = description,
                footer = footer
        )
    }

    fun info(
            channel: TextChannel,
            title: String? = null,
            description: String? = null
    ) {
        buildAndQueue(
                channel,
                color = INFO,
                title = title,
                description = description
        )
    }

    fun failure(
            channel: TextChannel,
            title: String = "Ops!",
            description: String? = null,
            footer: String? = null
    ) {
        buildAndQueue(
                channel,
                color = ERROR,
                title = title,
                description = description,
                footer = footer
        )
    }

    private fun buildAndQueue(
            channel: TextChannel,
            color: Color? = null,
            title: String? = null,
            description: String? = null,
            footer: String? = null
    ) {
        EmbedBuilder().apply {

            color?.let { setColor(color) }
            title?.let { setTitle(it) }
            description?.let { setDescription(it) }
            footer?.let { setFooter(it) }

        }.build().queue(channel)
    }

}