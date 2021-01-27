package br.com.gmfonseca.music.application.listener

import br.com.gmfonseca.music.business.scheduler.LeaveVoiceScheduler
import br.com.gmfonseca.shared.util.EmbedMessage
import br.com.gmfonseca.shared.util.Emoji
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 22/12/2020.
 */
class LeaveVoiceListener(private val channel: TextChannel) : LeaveVoiceScheduler.ILeaveVoiceListener {

    override fun onLeave(guildId: Long, channelId: Long) {
        EmbedMessage.info(
            channel,
            title = "Opa!",
            description = "Estou saindo aqui irmão! Até a próxima ${Emoji.WINKING}"
        ) { msg ->
            LAST_MESSAGE_ID.remove(guildId)?.let {
                channel.deleteMessageById(it).queue()
            }
            LAST_MESSAGE_ID[guildId] = msg.idLong
        }
    }

    companion object {
        private val LAST_MESSAGE_ID = mutableMapOf<Long, Long>()
    }
}