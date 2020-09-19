package br.com.gmfonseca.business.command.play

import br.com.gmfonseca.application.handler.AudioSenderHandler
import br.com.gmfonseca.business.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import java.awt.Color

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
class PlayCommand : Command("play") {

    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val embedMessage = EmbedBuilder()

        if (args.isEmpty()) {
            embedMessage.setColor(Color.RED).setTitle("Comando inválido").setDescription("Por favor, use $this <Youtube URL ou Nome do Video>")
        } else {
            val guild = channel.guild
            val voiceChannel = guild.voiceChannels.find { voiceChannel ->
                voiceChannel.members.find { it.user.idLong == author.idLong } != null
            }

            if (voiceChannel == null) {
                embedMessage.setColor(Color.RED).setDescription("Você não está conectado em um canal de voz!")
            } else {
                with(guild.audioManager) {
                    sendingHandler = AudioSenderHandler()
                    openAudioConnection(voiceChannel)
                }
                embedMessage.setColor(Color.ORANGE).setDescription("Opa! Já vou lançar a braba camarada")
            }
        }

        channel.sendMessage(embedMessage.build()).queue()

        return true
    }
}