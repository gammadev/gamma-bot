package br.com.gmfonseca.business.manager

import br.com.gmfonseca.application.handler.audio.AudioSenderHandler
import br.com.gmfonseca.application.handler.audio.TrackScheduler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager


/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
class GuildMusicManager(manager: AudioPlayerManager) {
    private val player = manager.createPlayer()
    val scheduler = TrackScheduler(player)
    val audioSenderHandler = AudioSenderHandler(player)

    init {
        player.addListener(scheduler)
    }
}