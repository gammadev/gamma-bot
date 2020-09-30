package br.com.gmfonseca.application.handler.message

import br.com.gmfonseca.business.utils.ext.getCommand
import br.com.gmfonseca.business.utils.ext.getCommandArgs
import br.com.gmfonseca.business.utils.ext.isCommand
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
class GuildMessageHandler : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        super.onGuildMessageReceived(event)

        if (event.author.isBot) return

        val rawMessage = event.message.contentRaw

        if (rawMessage.isCommand()) {
            rawMessage.getCommand().onCommand(event.author, event.channel, rawMessage.getCommandArgs())
        }
    }

}