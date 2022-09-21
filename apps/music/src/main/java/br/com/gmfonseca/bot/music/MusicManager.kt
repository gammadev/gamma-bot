package br.com.gmfonseca.bot.music

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.core.discord.managers.AppManager
import br.com.gmfonseca.bot.music.data.repositories.audioplayer.AudioPlayerRepository
import br.com.gmfonseca.bot.music.data.repositories.audioplayer.AudioPlayerRepositoryImpl
import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import br.com.gmfonseca.bot.music.factories.AudioPlayerManagerFactoryImpl
import br.com.gmfonseca.bot.music.generated.Statics
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object MusicManager : AppManager {

    val PLAYER_MANAGER = DefaultAudioPlayerManager()
    private val audioPlayerRepository: AudioPlayerRepository =
        AudioPlayerRepositoryImpl(AudioPlayerManagerFactoryImpl(PLAYER_MANAGER))

    fun getMusicManager(guildId: String): AudioPlayerManagerDelegate = audioPlayerRepository[guildId]
    fun getMusicManager(guildId: Long): AudioPlayerManagerDelegate = audioPlayerRepository[guildId]

    fun clearMusicManager(guildId: String): AudioPlayerManagerDelegate? = audioPlayerRepository.remove(guildId.toLong())

    override fun init(jda: JDA): Boolean = run {
        CommandManager.registerCommands(Statics.COMMANDS)
        AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)

        true
    }
}
