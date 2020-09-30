package br.com.gmfonseca.application.handler.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame
import net.dv8tion.jda.api.audio.AudioSendHandler
import java.nio.ByteBuffer

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class AudioSenderHandler(private val audioPlayer: AudioPlayer) : AudioSendHandler {
    private val buffer = ByteBuffer.allocate(1024)
    private val frame = MutableAudioFrame()

    init {
        frame.setBuffer(buffer)
    }

    override fun canProvide(): Boolean {
        return audioPlayer.provide(frame)
    }

    override fun provide20MsAudio(): ByteBuffer {
        return buffer.also { it.flip() }
    }

    override fun isOpus(): Boolean {
        return true
    }

}