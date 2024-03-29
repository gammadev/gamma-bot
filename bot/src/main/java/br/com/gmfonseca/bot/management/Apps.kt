package br.com.gmfonseca.bot.management

import br.com.gmfonseca.bot.core.discord.managers.AppManager
import br.com.gmfonseca.bot.music.MusicManager
import net.dv8tion.jda.api.JDA

object Apps : BotManagers<AppManager> {

    override operator fun invoke(): List<AppManager> = listOf(
        MusicManager,
    )

    override fun initializeAll(jda: JDA) = this().all { it.init(jda) }
}
