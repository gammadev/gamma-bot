package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.music.business.manager.GuildMusicManager
import br.com.gmfonseca.generated.Statics
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object MusicManager {

    const val COMMAND_PREFIX = '>'
    val PLAYER_MANAGER = DefaultAudioPlayerManager()

    private val guildsMusicManager = mutableMapOf<String, GuildMusicManager>()

    fun getMusicManager(guildId: String): GuildMusicManager {
        return guildsMusicManager[guildId] ?: GuildMusicManager(PLAYER_MANAGER).also {
            guildsMusicManager[guildId] = it
        }
    }

    fun getMusicManager(guildId: Long): GuildMusicManager {
        return getMusicManager("$guildId")
    }

    fun clearMusicManager(guildId: String): GuildMusicManager? {
        return guildsMusicManager.remove(guildId)
    }

    fun init() {
        CommandManager.registerCommands(Statics.COMMANDS)
        AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)
    }
}
