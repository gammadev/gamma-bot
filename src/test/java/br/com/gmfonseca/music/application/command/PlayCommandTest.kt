package br.com.gmfonseca.music.application.command

import br.com.gmfonseca.music.application.handler.audio.TrackScheduler
import br.com.gmfonseca.music.business.manager.GuildMusicManager
import br.com.gmfonseca.shared.util.EmbedMessage
import io.mockk.*
import io.mockk.impl.annotations.MockK
import net.dv8tion.jda.api.entities.*
import org.junit.*
import test.data.GUILD_ID
import test.data.SOME_ARGUMENT
import test.data.SOME_YOUTUBE_VIDEO_LINK
import test.data.USER_ID_LONG
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