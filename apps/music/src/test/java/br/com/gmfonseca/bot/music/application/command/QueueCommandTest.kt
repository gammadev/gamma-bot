package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.music.MusicManager
import br.com.gmfonseca.bot.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.bot.music.domain.managers.AudioPlayerManagerDelegate
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.requests.restaction.MessageAction
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import test.data.GUILD_ID
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class QueueCommandTest {

    // region setup
    private val guildMusicManager = mockk<AudioPlayerManagerDelegate>()
    private val guild = mockk<Guild>()
    private val message = mockk<Message>()
    private val channel = mockk<TextChannel>()
    private val scheduler = mockk<TrackScheduler>()
    private val msgAction = mockk<MessageAction>()

    private lateinit var queueCommand: QueueCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        queueCommand = QueueCommand()

        every { MusicManager.getMusicManager(GUILD_ID) } returns guildMusicManager
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

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            mockkObject(MusicManager)
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            unmockkObject(MusicManager)
        }
    }
}
