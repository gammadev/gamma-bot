package br.com.gmfonseca.bot.core

import java.util.logging.Level
import java.util.logging.Logger

object Logger {
    val instance: Logger = Logger.getGlobal()

    inline fun error(throwable: Throwable, message: String) {
        instance.log(
            Level.SEVERE,
            message
        )
        throwable.printStackTrace()
    }

    inline fun warn(message: String) {
        instance.log(Level.WARNING, message)
    }
}
