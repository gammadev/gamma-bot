package br.com.gmfonseca.bot.music.factories

import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

interface AudioPlayerManagerFactory {
    operator fun invoke(): AudioPlayerManagerDelegate
}

class AudioPlayerManagerFactoryImpl(private val audioPlayerManager: AudioPlayerManager) : AudioPlayerManagerFactory {
    override fun invoke() = AudioPlayerManagerDelegate(audioPlayerManager)
}
