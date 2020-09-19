package br.com.gmfonseca.application.handler

import net.dv8tion.jda.api.audio.AudioSendHandler
import java.nio.ByteBuffer
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class AudioSenderHandler : AudioSendHandler {

    private val queue = ConcurrentLinkedQueue<ByteArray>()

    override fun canProvide(): Boolean {
        return queue.isNotEmpty()
    }

    override fun provide20MsAudio(): ByteBuffer? {
        return queue.poll()?.let { ByteBuffer.wrap(it) }
    }

}