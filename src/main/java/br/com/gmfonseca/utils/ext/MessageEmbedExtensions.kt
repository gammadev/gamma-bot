package br.com.gmfonseca.utils.ext

import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 26/09/2020.
 */
fun MessageEmbed.queue(channel: TextChannel) {
    channel.sendMessage(this).queue()
}