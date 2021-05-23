package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.music.application.handler.message.GuildMessageHandler
import br.com.gmfonseca.bot.shared.exceptions.IllegalCommandClassException
import br.com.gmfonseca.bot.utils.Emoji.THUMBSUP
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.logging.Level
import java.util.logging.Logger
import javax.security.auth.login.LoginException
import kotlin.reflect.jvm.jvmName

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object DiscordApp {

    private val PLAYER_MANAGER = DefaultAudioPlayerManager()

    private lateinit var INSTANCE: JDA

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            INSTANCE = JDABuilder.createDefault(args[0])
                .setActivity(Activity.playing("sua mãe pela janela $THUMBSUP"))
                .build()
            addEventListener(GuildMessageHandler())
            AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)
            MusicManager.init()
        } catch (e: IndexOutOfBoundsException) {
            logSevere(
                e,
                "Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'"
            )
        } catch (e: LoginException) {
            logSevere(e, "Couldn't login with given token '${args[0]}'. Cause: ${e.message}")
        } catch (e: IllegalCommandClassException) {
            logSevere(
                e,
                "Failed to create an instance for class ${e.klass.jvmName}, when mapping commands. Cause: ${e.message}"
            )
        } catch (e: Throwable) {
            logSevere(e, "Whoops, something went wrong on build JDA Instance: ${e.message}")
        }
    }

    private fun addEventListener(vararg listeners: ListenerAdapter) {
        INSTANCE.addEventListener(*listeners)
    }

    private fun logSevere(throwable: Throwable, message: String) {
        Logger.getGlobal().log(
            Level.SEVERE,
            message
        )
        throwable.printStackTrace()
    }
}