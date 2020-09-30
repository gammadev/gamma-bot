package br.com.gmfonseca.application.listener

import br.com.gmfonseca.application.handler.audio.TrackScheduler
import br.com.gmfonseca.business.utils.ext.queue
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color

/**
 * Created by Gabriel Fonseca on 27/09/2020.
 */
class TrackSchedulerListener(private val channel: TextChannel) : TrackScheduler.ITrackSchedulerListener {

    override fun onNextTrack(track: AudioTrack) {
        EmbedBuilder()
                .setColor(Color.ORANGE)
                .setDescription("Tocando **${track.info.title}** agora!")
                .setFooter(track.info.author)
                .build()
                .queue(channel)
    }
}