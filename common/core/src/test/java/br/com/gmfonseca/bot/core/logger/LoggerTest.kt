package br.com.gmfonseca.bot.core.logger

import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.logging.Level
import java.util.logging.Logger as JLogger

class LoggerTest {

    private val jLogger = mockk<JLogger>()
    private val logger = Logger(jLogger)

    @Test
    fun `logging severe, should log SEVERE level message`() {
        // Setup
        val throwable = mockk<Throwable>()
        justRun { jLogger.log(Level.SEVERE, MESSAGE) }
        justRun { throwable.printStackTrace() }

        // Run
        logger.logSevere(throwable, MESSAGE)

        // Assert
        verify {
            jLogger.log(Level.SEVERE, MESSAGE)
            throwable.printStackTrace()
        }
    }

    private companion object {
        const val MESSAGE = "this is a message"
    }
}
