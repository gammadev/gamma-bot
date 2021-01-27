package br.com.gmfonseca.music.application.command

import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Gabriel Fonseca on 24/01/2021.
 */
class ClearCommandTest {

    // region setup

    @MockK
    private lateinit var message: Message

    @MockK
    private lateinit var channel: TextChannel

    private lateinit var clearCommand: ClearCommand

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        clearCommand = ClearCommand()


    }

    @After
    fun teardown() {
        confirmVerified(message, channel)
    }

    // endregion

    // region onCommand

    @Test
    fun testOnCommand() {
        // Mock
        every { }

        // Run
        clearCommand.onCommand(message, channel, emptyList())

        // Assert

    }

    // endregion

}