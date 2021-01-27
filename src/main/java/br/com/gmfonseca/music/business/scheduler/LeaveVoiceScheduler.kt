package br.com.gmfonseca.music.business.scheduler

import br.com.gmfonseca.DiscordApp
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Gabriel Fonseca on 13/12/2020.
 */
class LeaveVoiceScheduler private constructor(
    private val guildId: Long,
    private val channelId: Long,
    private val automatic: Boolean = false,
    private val listener: ILeaveVoiceListener? = null
) : Runnable {

    override fun run() {
        DiscordApp.getConnectVoice(guildId)?.run {
            if (automatic || (idLong == channelId && members.size == 1)) {
                DiscordApp.leaveConnectedVoice(guildId)?.run {
                    listener?.onLeave(guildId, channelId)
                    DiscordApp.removeLatestTextChannel(guildId)
                }
            }
        }
    }

    private fun schedule() {
        ScheduledThreadPoolExecutor(1)
            .schedule(this, 5, TimeUnit.SECONDS)
    }

    interface ILeaveVoiceListener {
        fun onLeave(guildId: Long, channelId: Long)
    }

    companion object {
        fun schedule(guildId: Long, channelId: Long, listener: ILeaveVoiceListener? = null) {
            DiscordApp.getConnectVoice(guildId)?.run {
                if (channelId == idLong && members.size <= 1) {
                    LeaveVoiceScheduler(guildId, idLong, listener = listener).schedule()
                }
            }
        }

        fun schedule(guildId: Long, listener: ILeaveVoiceListener? = null) {
            DiscordApp.getConnectVoice(guildId)?.run { // TODO: it can don't work well if i start another track after thrown this one
                LeaveVoiceScheduler(guildId, idLong, automatic = true, listener = listener).schedule()
            }
        }
    }

}