package br.com.gmfonseca.bot.battleship.application.command

import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.commandmanager.commands.BaseCommand

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "battleship", aliases = ["bs"])
class BattleshipCommand : BaseCommand() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        return true
    }
}
