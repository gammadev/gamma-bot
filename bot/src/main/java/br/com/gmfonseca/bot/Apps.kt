package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.core.discord.AppManager
import net.dv8tion.jda.api.JDA

object Apps {

    operator fun invoke(): List<AppManager> = listOf(
        CommandManager,
        MusicManager
    )

    fun initializeAll(jda: JDA) = this().all { it.init(jda) }
}
