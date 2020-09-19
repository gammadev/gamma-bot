import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import javax.security.auth.login.LoginException

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
object DiscordApp {

    lateinit var INSTANCE: JDA; private set

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            INSTANCE = JDABuilder.createDefault(args[0]).build()
        } catch (e: LoginException) {
            print("Couldn't login with given token '${args[0]}'. Cause: ${e.message}")
        } catch (e: IndexOutOfBoundsException) {
            print("Please provide a valid bot token on execute the .jar, like 'java -jar discordbot.java YOUR_TOKEN_HERE'")
        } catch (e: Throwable) {
            print("Whoops, something went wrong on build JDA Instance: ${e.message}")
        }
    }

}