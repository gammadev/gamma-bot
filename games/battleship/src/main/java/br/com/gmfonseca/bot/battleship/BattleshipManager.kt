package br.com.gmfonseca.bot.battleship

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.core.discord.managers.GameManager
import br.com.gmfonseca.bot.battleship.generated.Statics
import net.dv8tion.jda.api.JDA

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object BattleshipManager : GameManager {

    override fun init(jda: JDA): Boolean = run {
        CommandManager.registerCommands(Statics.COMMANDS)

        true
    }
}
