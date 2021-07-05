package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.music.MusicManager
import br.com.gmfonseca.bot.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.bot.music.business.manager.GuildMusicManager
import br.com.gmfonseca.bot.utils.Emoji
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
import net.dv8tion.jda.api.managers.AudioManager
import net.dv8tion.jda.api.requests.RestAction
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import test.data.GUILD_ID
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class LeaveCommandTest {

    // region setup
    private val guildMusicManager = mockk<GuildMusicManager>()
    private val guild = mockk<Guild>()
    private val message = mockk<Message>()
    private val channel = mockk<TextChannel>()
    private val scheduler = mockk<TrackScheduler>()
    private val reactionRestAction = mockk<RestAction<Void>>()
    private val audioManager = mockk<AudioManager>()

    private lateinit var leaveCommand: LeaveCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        leaveCommand = LeaveCommand()
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
    fun testOnCommand_givenValidParams_withExistentMusicManager_shouldReturnsTrue() {
        // Mock
        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { guild.audioManager } returns audioManager
        every { message.addReaction(Emoji.WAVE) } returns reactionRestAction
        every { MusicManager.clearMusicManager(GUILD_ID) } returns guildMusicManager
        every { guildMusicManager.scheduler } returns scheduler
        every { message.addReaction(Emoji.WAVE) } returns reactionRestAction

        justRun { scheduler.stop() }
        justRun { audioManager.closeAudioConnection() }
        justRun { reactionRestAction.queue() }

        // Run
        val result = leaveCommand.onCommand(message, channel, emptyList())

        // Assert
        assertTrue { result }
        verify(exactly = 1) {
            channel.guild
            guild.id
            guildMusicManager.scheduler
            scheduler.stop()
            guild.audioManager
            audioManager.closeAudioConnection()
            message.addReaction(Emoji.WAVE)
        }
    }

    @Test
    fun testOnCommand_givenValidParams_withNonExistentMusicManager_shouldReturnsFalse() {
        // Mock
        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { MusicManager.clearMusicManager(GUILD_ID) } returns null

        // Run
        val result = leaveCommand.onCommand(message, channel, emptyList())

        // Assert
        assertFalse { result }
        verify(exactly = 1) {
            channel.guild
            guild.id
        }
        verify(exactly = 0) {
            guildMusicManager.scheduler
            scheduler.stop()
            guild.audioManager
            audioManager.closeAudioConnection()
            message.addReaction(Emoji.WAVE)
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
