package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.MusicManager
import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.music.application.listener.TrackSchedulerListener
import br.com.gmfonseca.bot.shared.REGEX_FLOAT_ONLY
import br.com.gmfonseca.bot.shared.REGEX_INTEGER_ONLY
import br.com.gmfonseca.bot.shared.command.Command
import br.com.gmfonseca.bot.shared.util.EmbedMessage
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import kotlin.math.floor

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@CommandHandler(name = "volume", aliases = ["v"])
class VolumeCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        val guildId = channel.guild.idLong
        val scheduler = MusicManager.getMusicManager(guildId).scheduler.apply {
            listener = TrackSchedulerListener(channel)
        }

        if (args.isEmpty()) {
            return sendMessage(
                title = "Volume",
                description = "O volume atual é **${scheduler.volume}%**",
                channel = channel
            )
        }

        val volumeString = args.first().replace(",", ".")
        val volume = with(volumeString) {
            when {
                matches(Regex(REGEX_INTEGER_ONLY)) -> toInt()
                matches(Regex(REGEX_FLOAT_ONLY)) -> {
                    val volume = if (length == 1) 0F else toFloat()

                    volume.let {
                        floor(if (it > 1.0) it else it * 100)
                    }
                }
                else -> null
            }?.toInt() ?: return onWrongCommand(channel)
        }

        return if (scheduler.switchVolume(volume)) {
            sendMessage(
                title = "Volume alterado",
                description = "Agora o volume é de **$volume%**",
                channel = channel
            )
        } else {
            onWrongCommand(channel)
        }
    }

    private fun sendMessage(title: String? = null, description: String?, channel: TextChannel): Boolean {
        EmbedMessage.success(channel, title = title, description = description) {
            val guildId = channel.guild.idLong
            LAST_MESSAGE_ID[guildId]?.let { msgId ->
                channel.deleteMessageById(msgId).queue()
            }
            LAST_MESSAGE_ID[guildId] = it.idLong
        }

        return true
    }

    private fun onWrongCommand(channel: TextChannel): Boolean {
        super.onWrongCommand(channel, "<0 - 100>")
        return false
    }

    companion object {
        private val LAST_MESSAGE_ID = mutableMapOf<Long, Long>()
    }

}