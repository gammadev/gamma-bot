package br.com.gmfonseca.utils.ext

import kotlin.math.floor

/**
 * Created by Gabriel Fonseca on 26/10/2020.
 */
const val HOUR = 1_000 * 60 * 60

fun Long.takeHoursFromMs(): Double {
    return toDouble() / HOUR
}

fun Long.takeMinutesFromMs(): Double {
    val hours = takeHoursFromMs()

    return (hours - hours.toInt()) * 60
}

fun Long.takeSecondsFromMs(): Double {
    val minutes = takeMinutesFromMs()

    return (minutes - minutes.toInt()) * 60
}

fun Long.msToReadableTime(): String {
    val hours = floor(takeHoursFromMs())
    val formattedHours = hours.formatNumber()
    val minutes = floor(takeMinutesFromMs()).formatNumber()
    val seconds = floor(takeSecondsFromMs()).formatNumber()

    return if (hours != 0.0) {
        "$formattedHours:$minutes:$seconds"
    } else {
        "$minutes:$seconds"
    }
}