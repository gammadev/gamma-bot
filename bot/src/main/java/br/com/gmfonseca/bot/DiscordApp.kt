package br.com.gmfonseca.bot

import br.com.gmfonseca.bot.commandmanager.exceptions.IllegalCommandClassException
import br.com.gmfonseca.bot.core.Logger.error
import br.com.gmfonseca.bot.core.discord.exceptions.MissingBotTokenException
import br.com.gmfonseca.bot.management.Apps
import br.com.gmfonseca.bot.management.Managers
import br.com.gmfonseca.bot.management.Plugins
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
            startBot(args)
        } catch (e: MissingBotTokenException) {
            error(e, MISSING_TOKEN_MESSAGE)
        } catch (e: LoginException) {
            error(e, LOGIN_FAIL_MESSAGE.format(args[0], e.message))
        } catch (e: IllegalCommandClassException) {
            error(e, ILLEGAL_COMMAND_CLASS_MESSAGE.format(e.klass.jvmName, e.message))
        }
    }

    private fun startBot(args: Array<String>) {
        require(args.isNotEmpty()) { throw MissingBotTokenException() }

        INSTANCE = createJdaInstance(args)

        Managers.initializeAll(INSTANCE, Plugins, Apps)
    }

    private fun createJdaInstance(args: Array<String>) = JDABuilder.createDefault(args[0])
        .setActivity(Activity.playing("sua mãe pela janela $THUMBSUP"))
        .build()

    private const val MISSING_TOKEN_MESSAGE =
        "Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'"

    private const val ILLEGAL_COMMAND_CLASS_MESSAGE =
        "Failed to create an instance for class %1\$s, when mapping commands. Cause: %2\$s"

    private const val LOGIN_FAIL_MESSAGE = "Couldn't login with given token '%1\$s'. Cause: %2\$s"
}
