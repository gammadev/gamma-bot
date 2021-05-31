package br.com.gmfonseca.bot.management

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.core.discord.managers.PluginManager
import net.dv8tion.jda.api.JDA

object Plugins : BotManagers<PluginManager> {

    override operator fun invoke(): List<PluginManager> = listOf(
        CommandManager
    )

    override fun initializeAll(jda: JDA) = this().all { it.init(jda) }
}
