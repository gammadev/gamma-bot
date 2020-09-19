package br.com.gmfonseca.business.command

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class PlayCommand : Command("play") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val embedMessage = EmbedBuilder()
                .setDescription("Opa! Já vou lançar a braba camarada")
                .build()

        channel.sendMessage(embedMessage).queue()

        return true
    }
}