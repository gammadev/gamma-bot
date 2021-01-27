package br.com.gmfonseca

import br.com.gmfonseca.music.business.manager.GuildMusicManager
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.util.ClassMapper
import br.com.gmfonseca.shared.util.Emoji.THUMBSUP
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.VoiceChannel
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.logging.Level
import java.util.logging.Logger
import javax.security.auth.login.LoginException

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object DiscordApp {

    const val COMMAND_PREFIX = '>'

    val PLAYER_MANAGER = DefaultAudioPlayerManager()

    private lateinit var INSTANCE: JDA

    val id; get() = INSTANCE.selfUser.id

    private val guildsMusicManager = mutableMapOf<Long, GuildMusicManager>()
    private val guildsConnectedVoiceChannel = mutableMapOf<Long, VoiceChannel>()
    private val guildsLatestTextChannel = mutableMapOf<Long, TextChannel>()

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            INSTANCE = JDABuilder.createDefault(args[0])
                .setActivity(Activity.playing("sua mãe pela janela $THUMBSUP"))
                .build()

            mapClasses<Command> { Command.loadCommands(it) }
            mapClasses<ListenerAdapter> { INSTANCE.addEventListener(*it.toTypedArray()) }

            AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)
        } catch (e: IndexOutOfBoundsException) {
            Logger.getGlobal().log(
                Level.WARNING,
                "Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'"
            )
            e.printStackTrace()
        } catch (e: LoginException) {
            Logger.getGlobal().log(Level.WARNING, "Couldn't login with given token '${args[0]}'. Cause: ${e.message}")
            e.printStackTrace()
        } catch (e: Throwable) {
            Logger.getGlobal().log(Level.WARNING, "Whoops, something went wrong on build JDA Instance: ${e.message}")
            e.printStackTrace()
        }
    }

    fun getMusicManager(guildId: Long): GuildMusicManager {
        return guildsMusicManager[guildId] ?: GuildMusicManager(PLAYER_MANAGER).also {
            guildsMusicManager[guildId] = it
        }
    }

    fun removeMusicManager(guildId: Long): GuildMusicManager? {
        return guildsMusicManager.remove(guildId)
    }

    fun putConnectedVoice(guildId: Long, channel: VoiceChannel): VoiceChannel? {
        return guildsConnectedVoiceChannel.put(guildId, channel)
    }

    fun getConnectVoice(guildId: Long): VoiceChannel? {
        return guildsConnectedVoiceChannel[guildId]
    }

    fun leaveConnectedVoice(guildId: Long): VoiceChannel? {
        removeMusicManager(guildId)?.scheduler?.stop(null)

        return guildsConnectedVoiceChannel.remove(guildId)?.also {
            it.guild.audioManager.closeAudioConnection()
        }
    }

    fun putLatestTextChannel(guildId: Long, channel: TextChannel): TextChannel? {
        return guildsLatestTextChannel.put(guildId, channel)
    }

    fun getLatestTextChannel(guildId: Long): TextChannel? {
        return guildsLatestTextChannel[guildId]
    }

    fun removeLatestTextChannel(guildId: Long): TextChannel? {
        return guildsLatestTextChannel.remove(guildId)
    }

    private inline fun <reified T> mapClasses(
        classesRootPath: String = "br.com.gmfonseca",
        onFinish: (List<T>) -> Unit
    ) {
        onFinish(ClassMapper.mapClasses(classesRootPath))
    }

}