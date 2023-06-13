package br.com.gmfonseca.bot.music.application.command

import br.com.gmfonseca.bot.core.discord.EmbedMessage
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
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.VoiceChannel
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import test.data.GUILD_ID
import test.data.SOME_ARGUMENT
import test.data.USER_ID_LONG
import kotlin.test.assertFalse

/**
 * Created by Gabriel Fonseca on 29/01/2021.
 */
class PlayCommandTest {

    // region setup
    private val guildMusicManager = mockk<AudioPlayerManagerDelegate>()
    private val guild = mockk<Guild>()
    private val message = mockk<Message>()
    private val channel = mockk<TextChannel>()
    private val scheduler = mockk<TrackScheduler>()

    private lateinit var playCommand: PlayCommand

    @BeforeEach
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        playCommand = PlayCommand()

        justRun { EmbedMessage.failure(channel, any(), any(), any(), any()) }
    }

    @AfterEach
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
    fun testOnCommand_givenEmptyArgs_shouldReturnsFalse() {
        // Run
        val result = playCommand.onCommand(message, channel, emptyList())

        // Assert
        assertFalse(result)
        verify(atLeast = 1) {
            EmbedMessage.failure(channel, any(), any(), any(), any())
        }
    }

    @Test
    fun testOnCommand_givenNoneVoiceChannelInGuild_shouldReturnsFalse() {
        // Mock
        every { guild.id } returns GUILD_ID
        every { guild.voiceChannels } returns emptyList()

        // Run
        val result = playCommand.onCommand(message, channel, listOf(SOME_ARGUMENT))

        // Assert
        assertFalse(result)
        verify(atLeast = 1) {
            channel.guild
            EmbedMessage.failure(channel, any(), any(), any(), any())
        }
    }

    @Test
    fun testOnCommand_givenVoiceChannelsWithoutConnectedUsers_shouldReturnsFalse() {
        // Mock
        val voiceChannel = mockk<VoiceChannel>()
        val voiceChannels = listOf(voiceChannel)

        every { guild.id } returns GUILD_ID
        every { guild.voiceChannels } returns voiceChannels
        every { voiceChannel.members } returns emptyList()

        // Run
        val result = playCommand.onCommand(message, channel, listOf(SOME_ARGUMENT))

        // Assert
        assertFalse(result)
        verify(atLeast = 1) {
            channel.guild
            EmbedMessage.failure(channel, any(), any(), any(), any())
        }
    }

    @Test
    fun testOnCommand_givenVoiceChannelsWithConnectedUsers_butNotDesiredUser_shouldReturnsFalse() {
        // Mock
        val voiceChannel = mockk<VoiceChannel>()
        val voiceChannels = listOf(voiceChannel)
        val member = mockk<Member>()
        val members = listOf(member)
        val user = mockk<User>()
        val author = mockk<User>()

        every { guild.voiceChannels } returns voiceChannels
        every { voiceChannel.members } returns members
        every { member.user } returns user
        every { user.idLong } returns USER_ID_LONG
        every { message.author } returns author
        every { author.idLong } returns USER_ID_LONG + 1L

        // Run
        val result = playCommand.onCommand(message, channel, listOf(SOME_ARGUMENT))

        // Assert
        assertFalse(result)
        verify(atLeast = 1) {
            channel.guild
            EmbedMessage.failure(channel, any(), any(), any(), any())
        }
    }

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
