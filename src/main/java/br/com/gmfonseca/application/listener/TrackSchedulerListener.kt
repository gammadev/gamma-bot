package br.com.gmfonseca.application.listener

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.application.handler.audio.TrackScheduler
import br.com.gmfonseca.utils.EmbedMessage
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 27/09/2020.
 */
class TrackSchedulerListener(val channel: TextChannel) : TrackScheduler.ITrackSchedulerListener {

    override fun onNextTrack(track: AudioTrack) {
        EmbedMessage.success(
                channel,
                description = "Tocando **${track.info.title}** agora!",
                footer = track.info.author
        ) { message ->
            val guildId: Long = channel.guild.idLong
            DiscordApp.takeLastMessageId(guildId = guildId)?.let {
                channel.deleteMessageById(it).queue()
            }
            DiscordApp.putLastMessageId(guildId = guildId, messageId = message.idLong)
        }
    }

    override fun onWrongIndex(index: Int) {
        EmbedMessage.failure(
                channel,
                description = "A posição ${index + 1} não está na fila."
        )
    }
}