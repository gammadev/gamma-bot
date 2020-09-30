package br.com.gmfonseca.application.command

import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class UnknownCommand : Command("unknown") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        return true
    }
}