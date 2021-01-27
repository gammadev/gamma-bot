package br.com.gmfonseca.music.application.listener.event

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.listener.LeaveVoiceListener
import br.com.gmfonseca.music.business.scheduler.LeaveVoiceScheduler
import br.com.gmfonseca.shared.annotations.EventListener
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * Created by Gabriel Fonseca on 13/12/2020.
 */
@EventListener
class VoiceChannelListener : ListenerAdapter() {

    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {
        scheduleLeaveVoice(event.guild.idLong, event.channelLeft.idLong)
    }

    override fun onGuildVoiceMove(event: GuildVoiceMoveEvent) {
        scheduleLeaveVoice(event.guild.idLong, event.channelLeft.idLong)
    }

    override fun onGuildVoiceDeafen(event: GuildVoiceDeafenEvent) {
        with(event.member) {
            if (!event.isDeafened && user.isBot && id == DiscordApp.id) {
                deafen(true).queue()
            }
        }
    }

    private fun scheduleLeaveVoice(guildId: Long, voiceChannelId: Long) {
        val leaveVoiceListener = DiscordApp.getLatestTextChannel(guildId)?.let { LeaveVoiceListener(it) }

        LeaveVoiceScheduler.schedule(guildId, voiceChannelId, leaveVoiceListener)
    }

}