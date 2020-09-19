package br.com.gmfonseca.business.command

import br.com.gmfonseca.DiscordApp
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class UnknownCommand : Command("unknown") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val embedMessage = EmbedBuilder()
                .setTitle("Comando inválido")
                .setDescription("Opa! Você digitou algo errado, use ${DiscordApp.COMMAND_PREFIX}help")
                .build()

        channel.sendMessage(embedMessage).queue()

        return true
    }
}