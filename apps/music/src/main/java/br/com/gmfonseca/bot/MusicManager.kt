package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.core.discord.AppManager
import br.com.gmfonseca.bot.music.generated.Statics
import br.com.gmfonseca.bot.music.business.manager.GuildMusicManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object MusicManager : AppManager {

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

    override fun init(jda: JDA): Boolean = run {
        CommandManager.registerCommands(Statics.COMMANDS)
        AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)

        true
    }
}
