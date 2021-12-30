package br.com.gmfonseca.bot.music.application.listener

import br.com.gmfonseca.bot.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.bot.core.discord.EmbedMessage
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 27/09/2020.
 */
class TrackSchedulerListener(val channel: TextChannel) :
    TrackScheduler.TrackSchedulerListener {

    override fun onNextTrack(track: AudioTrack) {
        val guildId = channel.guild.idLong
        EmbedMessage.success(
            channel,
            description = "Tocando **${track.info.title}** agora!",
            footer = track.info.author
        ) { msg ->
            LAST_MESSAGE_ID[guildId]?.let {
                channel.deleteMessageById(it).queue()
            }
            LAST_MESSAGE_ID[guildId] = msg.idLong
        }
    }

    override fun onWrongIndex(index: Int) {
        EmbedMessage.failure(
            channel,
            description = "A posição ${index + 1} não está na fila."
        )
    }

    override fun onFinish() {
        LAST_MESSAGE_ID[channel.guild.idLong]?.let {
            channel.deleteMessageById(it).queue()
        }
    }

    companion object {
        private val LAST_MESSAGE_ID = mutableMapOf<Long, Long>()
    }
}
