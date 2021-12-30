package br.com.gmfonseca.bot.music.application.handler.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame
import net.dv8tion.jda.api.audio.AudioSendHandler
import java.nio.ByteBuffer

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class AudioSenderHandler(private val audioPlayer: AudioPlayer) : AudioSendHandler {
    private val buffer = ByteBuffer.allocate(BUFFER_BYTES_CAPACITY)
    private val frame = MutableAudioFrame().apply { setBuffer(buffer) }

    override fun isOpus(): Boolean = true
    override fun canProvide(): Boolean = audioPlayer.provide(frame)
    override fun provide20MsAudio(): ByteBuffer = buffer.also(ByteBuffer::flip)

    private companion object {
        const val BUFFER_BYTES_CAPACITY = 1024
    }
}
