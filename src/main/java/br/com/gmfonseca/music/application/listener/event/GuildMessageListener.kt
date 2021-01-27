package br.com.gmfonseca.music.application.listener.event

import br.com.gmfonseca.shared.annotations.EventListener
import br.com.gmfonseca.shared.util.ext.getCommand
import br.com.gmfonseca.shared.util.ext.getCommandArgs
import br.com.gmfonseca.shared.util.ext.isCommand
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
@EventListener
class GuildMessageListener : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (event.author.isBot) return

        with(event.message.contentRaw) {
            if (isCommand()) {
                getCommand().onCommand(event.message, event.channel, getCommandArgs())
            }
        }
    }
}