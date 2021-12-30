package br.com.gmfonseca.bot.music.data.repositories

import br.com.gmfonseca.bot.core.data.CacheableRepository
import br.com.gmfonseca.bot.core.discord.GuildId
import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import br.com.gmfonseca.bot.music.factories.AudioPlayerManagerFactory

class AudioPlayerRepository(
    private val audioPlayerManagerFactory: AudioPlayerManagerFactory
) : CacheableRepository<GuildId, AudioPlayerManagerDelegate> {

    private val guildsMusicManager = mutableMapOf<Long, AudioPlayerManagerDelegate>()

    operator fun get(guildId: String) = this[guildId.toLong()]

    override fun get(key: GuildId) = guildsMusicManager[key]
        ?: audioPlayerManagerFactory().also { guildsMusicManager[key] = it }

    override fun remove(key: Long): AudioPlayerManagerDelegate? {
        return guildsMusicManager.remove(key)
    }
}
