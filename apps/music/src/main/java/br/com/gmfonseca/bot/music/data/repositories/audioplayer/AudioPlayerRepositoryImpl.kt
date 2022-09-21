package br.com.gmfonseca.bot.music.data.repositories.audioplayer

import br.com.gmfonseca.bot.core.discord.GuildId
import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import br.com.gmfonseca.bot.music.factories.AudioPlayerManagerFactory

class AudioPlayerRepositoryImpl(
    private val audioPlayerManagerFactory: AudioPlayerManagerFactory
) : AudioPlayerRepository {

    private val guildsMusicManager = mutableMapOf<Long, AudioPlayerManagerDelegate>()

    override fun get(guildId: String) = this[guildId.toLong()]

    override fun get(key: GuildId) = guildsMusicManager[key]
        ?: audioPlayerManagerFactory.create().also { guildsMusicManager[key] = it }

    override fun remove(key: Long): AudioPlayerManagerDelegate? {
        return guildsMusicManager.remove(key)
    }
}