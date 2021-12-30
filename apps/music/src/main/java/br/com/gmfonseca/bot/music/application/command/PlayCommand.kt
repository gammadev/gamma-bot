package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.annotations.CommandHandler
import br.com.gmfonseca.bot.commandmanager.Command
import br.com.gmfonseca.bot.core.discord.EmbedMessage
import br.com.gmfonseca.bot.music.application.listener.YoutubeClientListener
import br.com.gmfonseca.bot.music.data.clients.YoutubeClient
import br.com.gmfonseca.bot.shared.REGEX_YOUTUBE
import br.com.gmfonseca.bot.shared.util.ext.connectVoice
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
@CommandHandler(name = "play", aliases = ["p"])
class PlayCommand : Command() {

    override fun onCommand(message: Message, channel: TextChannel, args: List<String>): Boolean {
        if (args.isEmpty()) {
            onWrongCommand(channel, "<youtube-link>")
            return false
        } else {
            val guild = channel.guild
            val voiceChannel = message.member?.voiceState?.channel

            if (voiceChannel == null) {
                EmbedMessage.failure(
                    channel,
                    description = "Você não está conectado em um canal de voz!"
                )
                return false
            } else {
                val youtubeClient = YoutubeClient(YoutubeClientListener(channel))

                if (args.first().matches(Regex(REGEX_YOUTUBE))) {
                    youtubeClient.download(args.first())
                } else {
                    youtubeClient.search(args.fold(StringBuilder()) { acc, cur -> acc.append(cur) }.toString())
                    EmbedMessage.info(
                        channel,
                        title = "Ainda não suportado!",
                        description = "Ainda não suportamos pesquisas, então por favor insira uma URL válida do Youtube."
                    )
                }

                guild.audioManager.connectVoice(channel, voiceChannel)
            }
        }

        return true
    }
}
