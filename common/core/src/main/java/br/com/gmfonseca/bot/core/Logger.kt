package br.com.gmfonseca.bot.core

import java.util.logging.Level
import java.util.logging.Logger

object Logger {
    fun logSevere(throwable: Throwable, message: String) {
        Logger.getGlobal().log(
            Level.SEVERE,
            message
        )
        throwable.printStackTrace()
    }
}
