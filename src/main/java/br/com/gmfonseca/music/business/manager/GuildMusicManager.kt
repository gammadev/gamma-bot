package br.com.gmfonseca.music.business.manager

import br.com.gmfonseca.music.application.handler.audio.AudioSenderHandler
import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import net.dv8tion.jda.api.entities.VoiceChannel


/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
class GuildMusicManager(manager: AudioPlayerManager) {
    private val player = manager.createPlayer()
    val scheduler = TrackScheduler(player)
    val audioSenderHandler = AudioSenderHandler(player)
    var voiceChannel: VoiceChannel? = null

    init {
        player.addListener(scheduler)
    }
}