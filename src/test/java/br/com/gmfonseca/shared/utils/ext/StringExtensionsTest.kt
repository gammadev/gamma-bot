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
    fun testIsCommand_givenEmptyString_shouldReturnFalse() {
        val result = "".isCommand()

        assertFalse { result }
    }

    @Test
    fun testIsCommand_givenNotEmptyString_withWrongPrefix_shouldReturnFalse() {
        val result = "¨".isCommand()

        assertFalse { result }
    }

    @Test
    fun testIsCommand_givenValidCommandPrefix_shouldReturnTrue() {
        val result = "$commandPrefix".isCommand()

        assertTrue { result }
    }

    @Test
    fun testIsCommand_givenValidString_withCommandPrefix_shouldReturnTrue() {
        val result = "$commandPrefix command".isCommand()

        assertTrue { result }
    }

    // endregion

    // region getCommandArgs

    @Test
    fun testGetCommandArgs_givenInvalidCommand_shouldReturnEmptyList() {
        val result = "".getCommandArgs()

        assertTrue { result.isEmpty() }
    }

    @Test
    fun testGetCommandArgs_givenValidCommand_withNoneArguments_shouldReturnEmptyList() {
        val result = "$commandPrefix".getCommandArgs()

        assertTrue { result.isEmpty() }
    }

    @Test
    fun testGetCommandArgs_givenValidCommand_withArguments_shouldReturnValidArgumentsList() {
        val expected = listOf("arg1", "arg2", "arg3")
        val result = "$commandPrefix arg1 arg2 arg3".getCommandArgs()

        assertEquals(expected, result)
    }

    // endregion

    // region equalsIgnoreCase

    @Test
    fun testEqualsIgnoreCase_givenDifferentString_withDifferentCase_shouldReturnTrue() {
        val result = "ABC123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun testEqualsIgnoreCase_givenDifferentString_withSameCase_shouldReturnTrue() {
        val result = "abc123".equalsIgnoreCase("efg345")

        assertFalse { result }
    }

    @Test
    fun testEqualsIgnoreCase_givenSameString_withDifferentCase_shouldReturnTrue() {
        val result = "aBc345".equalsIgnoreCase("Abc345")

        assertTrue { result }
    }

    @Test
    fun testEqualsIgnoreCase_givenSameString_withSameCase_shouldReturnTrue() {
        val result = "abc345".equalsIgnoreCase("abc345")

        assertTrue { result }
    }

    // endregion

    // region fill

    @Test
    fun testFill_givenEmptyString_withLowerLength_shouldReturnFilledString() {
        val result = "".fill(5)

        assertEquals("     ", result)
    }

    @Test
    fun testFill_givenValidString_withLowerLength_shouldReturnFilledEndString() {
        val result = "abc".fill(5, fillStart = false, fillChar = ' ')

        assertEquals("abc  ", result)
    }

    @Test
    fun testFill_givenValidString_withLowerLength_shouldReturnFilledStartString() {
        val result = "abc".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("  abc", result)
    }

    @Test
    fun testFill_givenValidString_withSameLength_shouldReturnGivenString() {
        val result = "abcde".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcde", result)
    }

    @Test
    fun testFill_givenValidString_withGreaterLength_shouldReturnGivenString() {
        val result = "abcdef".fill(5, fillStart = true, fillChar = ' ')

        assertEquals("abcdef", result)
    }

    // endregion

    // region truncate

    @Test
    fun testTruncate_givenInvalidMaxLength_shouldReturnGivenString() {
        val result = "invalid".truncate(-1)

        assertEquals("invalid", result)
    }

    @Test
    fun testTruncate_givenString_withSameLength_shouldReturnGivenString() {
        val result = "same".truncate(4)

        assertEquals("same", result)
    }

    @Test
    fun testTruncate_givenSmallerString_shouldReturnGivenString() {
        val result = "smallest".truncate(10)

        assertEquals("smallest", result)
    }

    @Test
    fun testTruncate_givenLongerString_shouldReturnTruncatedString() {
        val result = "long string".truncate(10)

        assertEquals("long stri…", result)
    }

    // endregion

    // region truncateOrFill

    @Test
    fun testTruncateOrFill_givenNullableString_shouldReturnFilledString() {
        val result = (null as String?).truncateOrFill(5, fillStart = true)

        assertEquals(" null", result)
    }

    @Test
    fun testTruncateOrFill_givenValidString_withSmallerLength_shouldReturnFilledString() {
        val result = "well".truncateOrFill(5, fillStart = false)

        assertEquals("well ", result)
    }

    @Test
    fun testTruncateOrFill_givenValidString_withSameLength_shouldReturnGivenString() {
        val result = "great".truncateOrFill(5)

        assertEquals("great", result)
    }

    @Test
    fun testTruncateOrFill_givenValidString_withGreaterLength_shouldReturnTruncatedString() {
        val result = "expected".truncateOrFill(5)

        assertEquals("expe…", result)
    }

    // endregion
}