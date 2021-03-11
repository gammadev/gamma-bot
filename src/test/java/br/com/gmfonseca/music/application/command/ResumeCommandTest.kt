package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import io.mockk.*
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import org.junit.*
import test.data.GUILD_ID
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class ResumeCommandTest {

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

    private lateinit var resumeCommand: ResumeCommand

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        resumeCommand = ResumeCommand()

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
        every { channel.guild } returns guild
        every { guild.id } returns GUILD_ID
        every { guildMusicManager.scheduler } returns scheduler
        justRun { scheduler.listener = any() }
        justRun { scheduler.resume(channel) }

        // Run
        val result = resumeCommand.onCommand(message, channel, emptyList())

        // Assert
        assertTrue(result)
        verify(exactly = 1) {
            channel.guild
            guild.id
            guildMusicManager.scheduler
            scheduler.listener = any()
            scheduler.resume(channel)
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