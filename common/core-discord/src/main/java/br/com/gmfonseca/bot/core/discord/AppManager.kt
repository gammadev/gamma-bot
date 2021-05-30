package br.com.gmfonseca.bot.core.discord

import net.dv8tion.jda.api.JDA

fun interface AppManager {
    fun init(jda: JDA): Boolean
}
