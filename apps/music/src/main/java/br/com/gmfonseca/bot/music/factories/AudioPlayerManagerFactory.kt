package br.com.gmfonseca.bot.music.factories

import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

interface AudioPlayerManagerFactory {
    fun create(): AudioPlayerManagerDelegate
}

class AudioPlayerManagerFactoryImpl(private val audioPlayerManager: AudioPlayerManager) : AudioPlayerManagerFactory {
    override fun create() = AudioPlayerManagerDelegate(audioPlayerManager)
}
