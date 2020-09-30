package br.com.gmfonseca

import br.com.gmfonseca.application.handler.message.GuildMessageHandler
import br.com.gmfonseca.business.manager.GuildMusicManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.ListenerAdapter
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
            INSTANCE = JDABuilder.createDefault(args[0]).setActivity(Activity.playing("sua mãe pela janela \uD83D\uDC4D")).build()

            addEventListener(GuildMessageHandler())
            AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER)

        } catch (e: IndexOutOfBoundsException) {
            print("Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'")
        } catch (e: LoginException) {
            print("Couldn't login with given token '${args[0]}'. Cause: ${e.message}")
        } catch (e: Throwable) {
            print("Whoops, something went wrong on build JDA Instance: ${e.message}")
        }
    }

    @Suppress("UNUSED")
    fun getMusicManager(guildId: Long): GuildMusicManager {
        return getMusicManager("$guildId")
    }

    fun getMusicManager(guildId: String): GuildMusicManager {
        return guildsMusicManager[guildId] ?: GuildMusicManager(PLAYER_MANAGER).also {
            guildsMusicManager[guildId] = it
        }
    }

    private fun addEventListener(vararg listeners: ListenerAdapter) {
        INSTANCE.addEventListener(*listeners)
    }


}