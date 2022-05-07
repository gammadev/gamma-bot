package br.com.gmfonseca.bot.core

import java.util.logging.Level
import java.util.logging.Logger

object Logger {
    private val logger = Logger.getGlobal()

    fun logError(throwable: Throwable, message: String) {
        logger.log(
            Level.SEVERE,
            message
        )
        throwable.printStackTrace()
    }
}
