package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import br.com.gmfonseca.shared.util.Emoji
import io.mockk.*
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.managers.AudioManager
import net.dv8tion.jda.api.requests.RestAction
import org.junit.*
import test.data.GUILD_ID
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class LeaveCommandTest {

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
    private lateinit var reactionRestAction: RestAction<Void>

    @MockK
    private lateinit var audioManager: AudioManager

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

    // region onCommand

    @Test
    fun testOnCommand_givenValidParams_withExistentMusicManager_shouldReturnsTrue() {
        // Mock
        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { guild.audioManager } returns audioManager
        every { message.addReaction(Emoji.WAVE) } returns reactionRestAction
        every { DiscordApp.clearMusicManager(GUILD_ID) } returns guildMusicManager
        every { guildMusicManager.scheduler } returns scheduler
        every { message.addReaction(Emoji.WAVE) } returns reactionRestAction

        justRun { scheduler.stop() }
        justRun { audioManager.closeAudioConnection() }
        justRun { reactionRestAction.queue() }

        // Run
        val result = leaveCommand.onCommand(message, channel, emptyList())

        // Assert
        assertTrue(result)
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
        every { DiscordApp.clearMusicManager(GUILD_ID) } returns null

        // Run
        val result = leaveCommand.onCommand(message, channel, emptyList())

        // Assert
        assertFalse(result)
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