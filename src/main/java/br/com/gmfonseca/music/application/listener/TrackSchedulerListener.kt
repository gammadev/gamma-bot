package br.com.gmfonseca.music.application.listener

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.business.scheduler.LeaveVoiceScheduler
import br.com.gmfonseca.music.business.scheduler.TrackScheduler
import br.com.gmfonseca.shared.util.EmbedMessage
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 27/09/2020.
 */
class TrackSchedulerListener(val channel: TextChannel) : TrackScheduler.ITrackSchedulerListener {

    override fun onNextTrack(track: AudioTrack) {
        val guildId = channel.guild.idLong

        scheduleLeaveVoice(guildId)

        EmbedMessage.success(
            channel,
            description = "Tocando **${track.info.title}** agora!",
            footer = track.info.author
        ) { msg ->
            LAST_MESSAGE_ID.remove(guildId)?.let {
                channel.deleteMessageById(it).queue()
            }
            LAST_MESSAGE_ID[guildId] = msg.idLong
        }
    }

    override fun onWrongIndex(index: Int) {
        scheduleLeaveVoice(channel.guild.idLong)

        EmbedMessage.failure(
            channel,
            description = "A posição ${index + 1} não está na fila."
        )
    }

    override fun onFinish() {
        val guildId = channel.guild.idLong

        scheduleLeaveVoice(guildId)

        LAST_MESSAGE_ID.remove(guildId)?.let {
            channel.deleteMessageById(it).queue()
        }
    }

    override fun onPause() {
        scheduleLeaveVoice(channel.guild.idLong)
    }

    override fun onStop() {
        scheduleLeaveVoice(channel.guild.idLong)
    }

    private fun scheduleLeaveVoice(guildId: Long) {
        val leaveVoiceListener = DiscordApp.getLatestTextChannel(guildId)?.let { LeaveVoiceListener(it) }

        LeaveVoiceScheduler.schedule(guildId, leaveVoiceListener)
    }

    companion object {
        private val LAST_MESSAGE_ID = mutableMapOf<Long, Long>()
    }
}