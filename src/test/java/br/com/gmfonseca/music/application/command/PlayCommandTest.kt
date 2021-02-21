package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import br.com.gmfonseca.shared.util.EmbedMessage
import io.mockk.*
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import org.junit.*
import kotlin.test.assertFalse

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class PlayCommandTest {

    // region setup

    @MockK
    private lateinit var guildMusicManager: GuildMusicManager

    @MockK
    private lateinit var guild: Guild

    @MockK
    private lateinit var message: Message

    @MockK
    private lateinit var channel: TextChannel

    @MockK
    private lateinit var scheduler: TrackScheduler

    private lateinit var playCommand: PlayCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        playCommand = PlayCommand()

        justRun { EmbedMessage.failure(channel, any(), any(), any(), any()) }
    }

    @After
    fun afterTest() {
        confirmVerified(
            message,
            channel,
            guild,
            guildMusicManager,
            scheduler
        )
    }

    // endregion

    // region onCommand

    @Test
    fun testOnCommand_givenEmptyArgs_shouldReturnsFalse() {
        // Run
        val result = playCommand.onCommand(message, channel, emptyList())

        // Assert
        assertFalse(result)
        verify(atLeast = 1) {
            EmbedMessage.failure(channel, any(), any(), any(), any())
        }
    }

    // endregion

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            mockkObject(EmbedMessage)
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            unmockkObject(EmbedMessage)
        }
    }

}