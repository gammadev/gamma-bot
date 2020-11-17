package br.com.gmfonseca

import br.com.gmfonseca.music.application.handler.message.GuildMessageHandler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.util.ClassMapper
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
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

    private val guildsMusicManager = mutableMapOf<String, GuildMusicManager>()

    private lateinit var INSTANCE: JDA

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            INSTANCE = JDABuilder.createDefault(args[0])
                .setActivity(Activity.playing("sua mãe pela janela \uD83D\uDC4D"))
                .build()

            mapClasses<Command> { loadCommands(it) }
            addEventListener(GuildMessageHandler())
            AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)
        } catch (e: IndexOutOfBoundsException) {
            Logger.getGlobal().log(
                Level.WARNING,
                "Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'"
            )
        } catch (e: LoginException) {
            Logger.getGlobal().log(Level.WARNING, "Couldn't login with given token '${args[0]}'. Cause: ${e.message}")
        } catch (e: Throwable) {
            Logger.getGlobal().log(Level.WARNING, "Whoops, something went wrong on build JDA Instance: ${e.message}")
        }
    }

    fun getMusicManager(guildId: String): GuildMusicManager {
        return guildsMusicManager[guildId] ?: GuildMusicManager(PLAYER_MANAGER).also {
            guildsMusicManager[guildId] = it
        }
    }

    fun getMusicManager(guildId: Long): GuildMusicManager {
        return getMusicManager("$guildId")
    }

    private fun addEventListener(vararg listeners: ListenerAdapter) {
        INSTANCE.addEventListener(*listeners)
    }

    private fun loadCommands(commands: List<Command>) {
        Command.loadCommands(commands)
    }

    private inline fun <reified T> mapClasses(
        classesRootPath: String = "br.com.gmfonseca",
        onFinish: (List<T>) -> Unit
    ) {
        val classSuffixName = T::class.simpleName
            ?: throw IllegalArgumentException("Invalid name null for given type class <T>")

        onFinish(ClassMapper.mapClasses(classesRootPath, classSuffixName))
    }

}