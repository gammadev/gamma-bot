package br.com.gmfonseca.bot.core.discord.extensions

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.TextChannel

fun MessageEmbed.queue(channel: TextChannel, onMessageSent: (Message) -> Unit) {
    channel.sendMessage(this).queue(onMessageSent)
}