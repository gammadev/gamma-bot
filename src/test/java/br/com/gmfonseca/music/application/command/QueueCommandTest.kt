package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import io.mockk.*
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.requests.restaction.MessageAction
import org.junit.*
import test.data.GUILD_ID
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class QueueCommandTest {

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

    @MockK
    private lateinit var msgAction: MessageAction

    private lateinit var queueCommand: QueueCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        queueCommand = QueueCommand()

        every { DiscordApp.getMusicManager(GUILD_ID) } returns guildMusicManager
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
    fun testOnCommand_givenValidParams_shouldRunsSuccessfully() {
        // Mock
        val msg = "queue"

        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { guildMusicManager.scheduler } returns scheduler
        every { scheduler.queueToString() } returns msg
        every { channel.sendMessage(msg) } returns msgAction
        justRun { msgAction.queue() }

        justRun { scheduler.listener = any() }

        // Run
        val result = queueCommand.onCommand(message, channel, emptyList())

        // Assert
        assertTrue(result)
        verify(exactly = 1) {
            channel.guild
            guild.id
            guildMusicManager.scheduler
            scheduler.listener = any()
            scheduler.queueToString()
            channel.sendMessage(msg)
            msgAction.queue()
        }
    }

    // endregion

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            mockkObject(DiscordApp)
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            unmockkObject(DiscordApp)
        }
    }

}