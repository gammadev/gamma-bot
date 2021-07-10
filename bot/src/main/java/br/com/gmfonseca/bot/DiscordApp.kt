package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.core.discord.exceptions.MissingBotTokenException
import br.com.gmfonseca.bot.core.logger.logSevere
import br.com.gmfonseca.bot.management.Apps
import br.com.gmfonseca.bot.management.Managers
import br.com.gmfonseca.bot.management.Plugins
import br.com.gmfonseca.bot.shared.exceptions.IllegalCommandClassException
import br.com.gmfonseca.bot.utils.Emoji.THUMBSUP
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import javax.security.auth.login.LoginException
import kotlin.reflect.jvm.jvmName

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object DiscordApp {

    private lateinit var INSTANCE: JDA

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            require(args.isNotEmpty()) { throw MissingBotTokenException() }

            INSTANCE = JDABuilder.createDefault(args[0])
                .setActivity(Activity.playing("sua mãe pela janela $THUMBSUP"))
                .build()

            Managers.initializeAll(INSTANCE, Plugins, Apps)
        } catch (e: MissingBotTokenException) {
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
        }
    }
}
