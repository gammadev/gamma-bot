package br.com.gmfonseca.shared.utils.ext

import br.com.gmfonseca.DiscordApp
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Gabriel Fonseca on 02/11/2020.
 */
class StringExtensionsTest {

    private val commandPrefix = DiscordApp.COMMAND_PREFIX

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
}