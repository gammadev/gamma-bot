package br.com.gmfonseca.bot.shared.util.ext

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */

/**
 * Fill [this] with [fillChar] to reach to [maxLength], or returns [this] if bigger than [maxLength]
 *
 * The flag [fillStart] validates if should fill with [fillChar] before [this].
 * @Example if [fillStart] then returns "    this" else "this    "
 *
 * @param maxLength the bigger allowed length for [this]
 * @param fillStart specifies where to fill
 * @param fillChar value to fill
 */
fun String.fill(maxLength: Int, fillStart: Boolean = false, fillChar: Char = ' '): String {
    val fillCount = maxLength - length
    val format = "%1$${fillCount}s"

    return when {
        fillCount <= 0 -> this
        fillStart -> "${format.format(fillChar)}$this"
        else -> "$this${format.format(fillChar)}"
    }
}

/**
 * Truncate string based on [maxLength], where if [this] is bigger than [maxLength],
 * then replaces character at {[maxLength]-1} with '…' and return. Otherwise returns [this].
 *
 * @param maxLength the bigger allowed length for [this]
 */
fun String.truncate(maxLength: Int): String {
    return if (maxLength in 1 until length) "${substring(0, maxLength - 1)}…" else this
}

/**
 * Truncate [this] if bigger than [maxLength]. Otherwise, fill with [fillChar] by [fillStart].
 *
 * @see String.truncate to check how truncate works
 * @see String.fill to check how fill works
 *
 * @param maxLength the bigger allowed length for [this]
 * @param fillStart specifies where to fill
 * @param fillChar value to fill
 */
fun String?.truncateOrFill(maxLength: Int, fillStart: Boolean = false, fillChar: Char = ' '): String {
    return when {
        this == null -> "null".fill(maxLength, fillStart)
        length < maxLength -> fill(maxLength, fillStart, fillChar)
        else -> truncate(maxLength)
    }
}

fun String.substringBetween(after: String, before: String): String {
    return this.substringAfter(after).substringBeforeLast(before)
}

infix fun String.equalsIgnoreCase(other: String): Boolean {
    return equals(other, ignoreCase = true)
}
