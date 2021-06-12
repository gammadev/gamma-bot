package br.com.gmfonseca.bot.commandmanager.handler.message

import br.com.gmfonseca.bot.commandmanager.ext.asCommand
import br.com.gmfonseca.bot.commandmanager.ext.commandArgs
import br.com.gmfonseca.bot.commandmanager.ext.isCommand
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
class GuildMessageHandler : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        super.onGuildMessageReceived(event)

        if (event.author.isBot) return

        with(event.message.contentRaw) {
            if (isCommand) {
                asCommand.onCommand(event.message, event.channel, commandArgs)
            }
        }
    }
}
