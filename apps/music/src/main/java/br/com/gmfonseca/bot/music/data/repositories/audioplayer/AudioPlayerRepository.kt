package br.com.gmfonseca.bot.music.data.repositories.audioplayer

import br.com.gmfonseca.bot.core.data.CacheableRepository
import br.com.gmfonseca.bot.core.discord.GuildId
import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate

interface AudioPlayerRepository : CacheableRepository<GuildId, AudioPlayerManagerDelegate> {
    operator fun get(guildId: String): AudioPlayerManagerDelegate
}