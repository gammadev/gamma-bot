package br.com.gmfonseca.bot.management

import br.com.gmfonseca.bot.core.discord.managers.Manager
import net.dv8tion.jda.api.JDA

object Managers {

    fun initializeAll(jda: JDA, vararg managers: BotManagers<out Manager>) = managers.all { it.initializeAll(jda) }
}
