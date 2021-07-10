package br.com.gmfonseca.bot.core.logger

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LoggerExtensionsTest {

    private val throwable = mockk<Throwable>()
    private val logger = mockk<Logger>()

    @BeforeEach
    fun beforeAll() {
        mockkObject(Logger)
    }

    @AfterEach
    fun teardownAll() {
        unmockkObject(Logger)
    }

    @Nested
    inner class LogSever {

        @Test
        fun test() {
            // Setup
            every { Logger.INSTANCE } returns logger
            justRun { logger.logSevere(throwable, MESSAGE) }

            // Run
            logSevere(throwable, MESSAGE)

            // Assert
            verify {
                Logger.INSTANCE
                logger.logSevere(throwable, MESSAGE)
            }
        }
    }

    companion object {
        const val MESSAGE = "this is a message"
    }

}
