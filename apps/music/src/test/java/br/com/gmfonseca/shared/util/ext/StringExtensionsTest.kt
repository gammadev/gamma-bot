package br.com.gmfonseca.shared.util.ext

import br.com.gmfonseca.bot.commandmanager.CommandManager
import br.com.gmfonseca.bot.commandmanager.UnknownCommand
import br.com.gmfonseca.bot.commandmanager.ext.asCommand
import br.com.gmfonseca.bot.commandmanager.ext.commandArgs
import br.com.gmfonseca.bot.commandmanager.ext.isCommand
import br.com.gmfonseca.bot.music.application.command.PlayCommand
import br.com.gmfonseca.bot.shared.util.ext.equalsIgnoreCase
import br.com.gmfonseca.bot.shared.util.ext.fill
import br.com.gmfonseca.bot.shared.util.ext.substringBetween
import br.com.gmfonseca.bot.shared.util.ext.truncate
import br.com.gmfonseca.bot.shared.util.ext.truncateOrFill
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 02/11/2020.
 */
class StringExtensionsTest {

    // region setup
    private val playCommand = mockk<PlayCommand>()

    private val commandPrefix = CommandManager.COMMAND_PREFIX

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        every { CommandManager.COMMANDS } returns mutableMapOf("play" to playCommand)
    }

    // endregion

    // region isCommand

    @Test
    fun givenEmptyStringShouldReturnFalse() {
        val result = "".isCommand

        assertFalse { result }
    }

    @Test
    fun givenNotEmptyStringWithWrongPrefixShouldReturnFalse() {
        val result = "¨".isCommand

        assertFalse { result }
    }

    @Test
    fun givenValidCommandPrefixShouldReturnTrue() {
        val result = commandPrefix.isCommand

        assertTrue { result }
    }

    @Test
    fun givenValidStringWithCommandPrefixShouldReturnTrue() {
        val result = "$commandPrefix command".isCommand

        assertTrue { result }
    }

    // endregion

    // region command
    @Test
    fun givenBlankString_shouldThrowsStringIndexOutOfBoundsException() {
        assertThrows<StringIndexOutOfBoundsException> { "".asCommand }
    }

    @Test
    fun givenCommandStringWithoutPrefix_shouldReturnsUnknownCommand() {
        // Mock
        every { CommandManager.findCommand("oin") } returns UnknownCommand

        // Run
        val result = "join".asCommand

        // Assert
        assertEquals(UnknownCommand, result)
        verify(exactly = 1) { CommandManager.findCommand("oin") }
    }

    @Test
    fun givenCommandStringWithPrefix_shouldReturnsCorrectlyCommand() {
        every { CommandManager.findCommand("play") } returns playCommand

        val result = "${commandPrefix}play".asCommand

        assertTrue(result is PlayCommand)
    }

    // endregion

    // region commandArgs

    @Test
    fun givenInvalidCommandShouldReturnEmptyList() {
        val result = "".commandArgs

        assertTrue { result.isEmpty() }
    }

    @Test
    fun givenValidCommandWithNoneArgumentsShouldReturnEmptyList() {
        val result = commandPrefix.commandArgs

        assertTrue { result.isEmpty() }
    }

    @Test
    fun givenValidCommandWithArgumentsShouldReturnValidArgumentsList() {
        val expected = listOf("arg1", "arg2", "arg3")
        val result = "$commandPrefix arg1 arg2 arg3".commandArgs

        assertEquals(expected, result)
    }

    // endregion

    // region fill

    @Test
    fun givenEmptyStringWithLowerLengthShouldReturnFilledString() {
        val result = "".fill(5)

        assertEquals("     ", result)
    }

    @Test
    fun givenValidStringWithLowerLengthShouldReturnFilledEndString() {
        val result = "abc".fill(5, fillStart = false, fillChar = ' ')

        assertEquals("abc  ", result)
    }

    @Test
    fun givenValidStringWithLowerLengthShouldReturnFilledStartString() {
        val result = "abc".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("  abc", result)
    }

    @Test
    fun testFill_givenValidStringWithSameLengthShouldReturnGivenString() {
        val result = "abcde".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcde", result)
    }

    @Test
    fun givenValidStringWithGreaterLengthShouldReturnGivenString() {
        val result = "abcdef".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcdef", result)
    }

    // endregion

    // region truncate

    @Test
    fun givenInvalidMaxLengthShouldReturnGivenString() {
        val result = "invalid".truncate(-1)

        assertEquals("invalid", result)
    }

    @Test
    fun givenStringWithSameLengthShouldReturnGivenString() {
        val result = "same".truncate(4)

        assertEquals("same", result)
    }

    @Test
    fun givenSmallerStringShouldReturnGivenString() {
        val result = "smallest".truncate(10)

        assertEquals("smallest", result)
    }

    @Test
    fun givenLongerStringShouldReturnTruncatedString() {
        val result = "long string".truncate(10)

        assertEquals("long stri…", result)
    }

    // endregion

    // region truncateOrFill

    @Test
    fun givenNullableStringShouldReturnFilledString() {
        val result = (null as String?).truncateOrFill(5, fillStart = true)

        assertEquals(" null", result)
    }

    @Test
    fun givenValidStringWithSmallerLengthShouldReturnFilledString() {
        val result = "well".truncateOrFill(5, fillStart = false)

        assertEquals("well ", result)
    }

    @Test
    fun testTruncateOrFill_givenValidStringWithSameLengthShouldReturnGivenString() {
        val result = "great".truncateOrFill(5)

        assertEquals("great", result)
    }

    @Test
    fun givenValidStringWithGreaterLengthShouldReturnTruncatedString() {
        val result = "expected".truncateOrFill(5)

        assertEquals("expe…", result)
    }

    // endregion

    // region substringBetween

    @Test
    fun givenEmptyString_shouldReturnsEmptyString() {
        val result = "".substringBetween("1", "2")

        assertEquals("", result)
    }

    @Test
    fun givenSequentialStringRemovingExtremes_shouldReturnsReducedString() {
        val result = "12345".substringBetween("1", "5")

        assertEquals("234", result)
    }

    @Test
    fun givenSequentialStringAndInvalidBeforeArgument_shouldReturnsReducedStringWithSameEnding() {
        val result = "12345".substringBetween("1", "6")

        assertEquals("2345", result)
    }

    @Test
    fun givenSequentialStringAndInvalidAfterArgument_shouldReturnsReducedStringWithSameStarting() {
        val result = "12345".substringBetween("0", "5")

        assertEquals("1234", result)
    }

    // endregion

    // region equalsIgnoreCase

    @Test
    fun givenDifferentStringWithDifferentCaseShouldReturnTrue() {
        val result = "ABC123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun givenDifferentStringWithSameCaseShouldReturnTrue() {
        val result = "abc123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun givenSameStringWithDifferentCaseShouldReturnTrue() {
        val result = "aBc345".equalsIgnoreCase("Abc345")

        assertTrue { result }
    }

    @Test
    fun givenSameStringWithSameCaseShouldReturnTrue() {
        val result = "abc345".equalsIgnoreCase("abc345")

        assertTrue { result }
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            mockkObject(CommandManager)
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            unmockkObject(CommandManager)
        }
    }
}
