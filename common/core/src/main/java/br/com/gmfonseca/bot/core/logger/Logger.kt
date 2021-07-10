package br.com.gmfonseca.bot.core.logger

import java.util.logging.Level
import java.util.logging.Logger

class Logger(private val logger: Logger) {
    fun logSevere(throwable: Throwable, message: String) {
        logger.log(
            Level.SEVERE,
            message
        )
        throwable.printStackTrace()
    }

    companion object {
        val INSTANCE = Logger(Logger.getGlobal())
    }
}
