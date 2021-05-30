package br.com.gmfonseca.bot.core.discord.managers

import net.dv8tion.jda.api.JDA

fun interface Manager {
    fun init(jda: JDA): Boolean
}
