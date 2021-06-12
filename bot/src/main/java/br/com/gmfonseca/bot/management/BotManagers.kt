package br.com.gmfonseca.bot.management

import br.com.gmfonseca.bot.core.discord.managers.Manager
import net.dv8tion.jda.api.JDA

interface BotManagers<T : Manager> {

    operator fun invoke(): List<T>

    fun initializeAll(jda: JDA): Boolean
}
