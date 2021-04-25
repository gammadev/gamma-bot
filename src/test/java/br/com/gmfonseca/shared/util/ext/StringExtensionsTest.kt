package br.com.gmfonseca.shared.util.ext

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.music.application.command.PlayCommand
import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.command.UnknownCommand
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.io.File
import java.io.File.separator
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 02/11/2020.
 */
class StringExtensionsTest {

    // region setup
    @MockK
    private lateinit var joinCommand: PlayCommand

    private val commandPrefix = DiscordApp.COMMAND_PREFIX

    @Before
    fun beforeTest() {
        MockKAnnotations.init(this, relaxed = true)

        every { Command.Companion.commands } returns listOf(joinCommand)
    }

    // endregion

    // region isCommand

    @Test
    fun testIsCommandGivenEmptyStringShouldReturnFalse() {
        val result = "".isCommand()

        assertFalse { result }
    }

    @Test
    fun testIsCommandGivenNotEmptyStringWithWrongPrefixShouldReturnFalse() {
        val result = "¨".isCommand()

        assertFalse { result }
    }

    @Test
    fun testIsCommandGivenValidCommandPrefixShouldReturnTrue() {
        val result = "$commandPrefix".isCommand()

        assertTrue { result }
    }

    @Test
    fun testIsCommandGivenValidStringWithCommandPrefixShouldReturnTrue() {
        val result = "$commandPrefix command".isCommand()

        assertTrue { result }
    }

    // endregion

    // region getCommand

    @Test(expected = StringIndexOutOfBoundsException::class)
    fun testGetCommand_givenBlankString_shouldThrowsStringIndexOutOfBoundsException() {
        "".getCommand()
    }

    @Test
    fun testGetCommand_givenCommandStringWithoutPrefix_shouldReturnsUnknownCommand() {
        // Mock
        every { Command.findCommand("oin") } returns UnknownCommand

        // Run
        val result = "join".getCommand()

        // Assert
        assertEquals(UnknownCommand, result)
        verify(exactly = 1) { Command.findCommand("oin") }
    }

    @Test
    fun testGetCommand_givenCommandStringWithPrefix_shouldReturnsCorrectlyCommand() {
        every { joinCommand.name } returns "join"

        val result = "${commandPrefix}join".getCommand()

        assertTrue(result is PlayCommand)
        verify(exactly = 1) { joinCommand.name }
    }

    // endregion

    // region getCommandArgs

    @Test
    fun testGetCommandArgsGivenInvalidCommandShouldReturnEmptyList() {
        val result = "".getCommandArgs()

        assertTrue { result.isEmpty() }
    }

    @Test
    fun testGetCommandArgsGivenValidCommandWithNoneArgumentsShouldReturnEmptyList() {
        val result = "$commandPrefix".getCommandArgs()

        assertTrue { result.isEmpty() }
    }

    @Test
    fun testGetCommandArgsGivenValidCommandWithArgumentsShouldReturnValidArgumentsList() {
        val expected = listOf("arg1", "arg2", "arg3")
        val result = "$commandPrefix arg1 arg2 arg3".getCommandArgs()

        assertEquals(expected, result)
    }

    // endregion

    // test mapFileToClassPath

    @Test
    fun testMapFileToClassPath_givenValidFilePath() {
        // Mock
        val file = mockk<File>()
        val name = "StringExtensions.kt"
        val path = StringBuilder("br")
            .append(separator, "com")
            .append(separator, "gmfonseca")
            .append(separator, "shared")
            .append(separator, "util")
            .append(separator, "ext")
            .append(separator, name)
            .toString()

        every { file.absolutePath } returns path
        every { file.name } returns name

        // Run
        val result = "br.com.gmfonseca".mapFileToClassPath(file)

        // Assert
        assertEquals("br.com.gmfonseca.shared.util.ext.StringExtensions", result)
        verifyAll {
            file.absolutePath
            file.name
        }
        confirmVerified(file)
    }

    // endregion

    // region fill

    @Test
    fun testFillGivenEmptyStringWithLowerLengthShouldReturnFilledString() {
        val result = "".fill(5)

        assertEquals("     ", result)
    }

    @Test
    fun testFillGivenValidStringWithLowerLengthShouldReturnFilledEndString() {
        val result = "abc".fill(5, fillStart = false, fillChar = ' ')

        assertEquals("abc  ", result)
    }

    @Test
    fun testFillGivenValidStringWithLowerLengthShouldReturnFilledStartString() {
        val result = "abc".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("  abc", result)
    }

    @Test
    fun testFillGivenValidStringWithSameLengthShouldReturnGivenString() {
        val result = "abcde".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcde", result)
    }

    @Test
    fun testFillGivenValidStringWithGreaterLengthShouldReturnGivenString() {
        val result = "abcdef".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcdef", result)
    }

    // endregion

    // region truncate

    @Test
    fun testTruncateGivenInvalidMaxLengthShouldReturnGivenString() {
        val result = "invalid".truncate(-1)

        assertEquals("invalid", result)
    }

    @Test
    fun testTruncateGivenStringWithSameLengthShouldReturnGivenString() {
        val result = "same".truncate(4)

        assertEquals("same", result)
    }

    @Test
    fun testTruncateGivenSmallerStringShouldReturnGivenString() {
        val result = "smallest".truncate(10)

        assertEquals("smallest", result)
    }

    @Test
    fun testTruncateGivenLongerStringShouldReturnTruncatedString() {
        val result = "long string".truncate(10)

        assertEquals("long stri…", result)
    }

    // endregion

    // region truncateOrFill

    @Test
    fun testTruncateOrFillGivenNullableStringShouldReturnFilledString() {
        val result = (null as String?).truncateOrFill(5, fillStart = true)

        assertEquals(" null", result)
    }

    @Test
    fun testTruncateOrFillGivenValidStringWithSmallerLengthShouldReturnFilledString() {
        val result = "well".truncateOrFill(5, fillStart = false)

        assertEquals("well ", result)
    }

    @Test
    fun testTruncateOrFillGivenValidStringWithSameLengthShouldReturnGivenString() {
        val result = "great".truncateOrFill(5)

        assertEquals("great", result)
    }

    @Test
    fun testTruncateOrFillGivenValidStringWithGreaterLengthShouldReturnTruncatedString() {
        val result = "expected".truncateOrFill(5)

        assertEquals("expe…", result)
    }

    // endregion

    // region substringBetween

    @Test
    fun testSubstringBetween_givenEmptyString_shouldReturnsEmptyString() {
        val result = "".substringBetween("1", "2")

        assertEquals("", result)
    }

    @Test
    fun testSubstringBetween_givenSequentialStringRemovingExtremes_shouldReturnsReducedString() {
        val result = "12345".substringBetween("1", "5")

        assertEquals("234", result)
    }

    @Test
    fun testSubstringBetween_givenSequentialStringAndInvalidBeforeArgument_shouldReturnsReducedStringWithSameEnding() {
        val result = "12345".substringBetween("1", "6")

        assertEquals("2345", result)
    }

    @Test
    fun testSubstringBetween_givenSequentialStringAndInvalidAfterArgument_shouldReturnsReducedStringWithSameStarting() {
        val result = "12345".substringBetween("0", "5")

        assertEquals("1234", result)
    }
    // endregion

    // region equalsIgnoreCase

    @Test
    fun testEqualsIgnoreCaseGivenDifferentStringWithDifferentCaseShouldReturnTrue() {
        val result = "ABC123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun testEqualsIgnoreCaseGivenDifferentStringWithSameCaseShouldReturnTrue() {
        val result = "abc123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun testEqualsIgnoreCaseGivenSameStringWithDifferentCaseShouldReturnTrue() {
        val result = "aBc345".equalsIgnoreCase("Abc345")

        assertTrue { result }
    }

    @Test
    fun testEqualsIgnoreCaseGivenSameStringWithSameCaseShouldReturnTrue() {
        val result = "abc345".equalsIgnoreCase("abc345")

        assertTrue { result }
    }

    // endregion

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            mockkObject(Command.Companion)
        }

        @JvmStatic
        @AfterClass
        fun afterClass() {
            unmockkObject(Command.Companion)
        }
    }
}