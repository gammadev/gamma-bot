package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.MusicManager
import br.com.gmfonseca.bot.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.bot.music.business.manager.GuildMusicManager
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
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import test.data.GUILD_ID
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class StopCommandTest {

    // region setup

    private val guildMusicManager = mockk<GuildMusicManager>()
    private val guild = mockk<Guild>()
    private val message = mockk<Message>()
    private val channel = mockk<TextChannel>()
    private val scheduler = mockk<TrackScheduler>()

    private lateinit var stopCommand: StopCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        stopCommand = StopCommand()

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
        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { guildMusicManager.scheduler } returns scheduler
        justRun { scheduler.listener = any() }
        justRun { scheduler.stop(channel) }

        // Run
        val result = stopCommand.onCommand(message, channel, emptyList())

        // Assert
        assertTrue(result)
        verify(exactly = 1) {
            channel.guild
            guild.id
            guildMusicManager.scheduler
            scheduler.listener = any()
            scheduler.stop(channel)
        }
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            mockkObject(MusicManager)
        }

        @BeforeClass
        @JvmStatic
        fun afterClass() {
            unmockkObject(MusicManager)
        }
    }
}
