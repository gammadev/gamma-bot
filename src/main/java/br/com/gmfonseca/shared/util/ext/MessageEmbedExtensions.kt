package br.com.gmfonseca.shared.util.ext

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 26/09/2020.
 */
fun MessageEmbed.queue(channel: TextChannel, onMessageSent: (Message) -> Unit) {
    channel.sendMessage(this).queue(onMessageSent)
}