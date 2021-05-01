package br.com.gmfonseca.shared.command

import br.com.gmfonseca.annotations.CommandHandler
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
@CommandHandler("")
object UnknownCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        return true
    }
}