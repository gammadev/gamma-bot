package br.com.gmfonseca.bot.shared

/**
 * Created by Gabriel Fonseca on 05/11/2020.
 */

// shared regex
private const val REGEX_PROTOCOL = "(?:(http)(|s)://)"
const val REGEX_INTEGER_ONLY = "(?:[0-9]+)"
const val REGEX_FLOAT_ONLY = "(?:([0-9]+|)\\.([0-9]+|))"

// youtube regex
private const val REGEX_YOUTUBE_DOMAIN = "(?:(www.|m.|music.|)youtube.com)/(watch|playlist)\\?[a-zA-Z0-9_\\-&=\\.?]+"
private const val REGEX_YOUTUBE_SHORTER_DOMAIN = "(?:youtu.be/[a-zA-Z0-9_\\-&=.?]+)"
const val REGEX_YOUTUBE = "$REGEX_PROTOCOL($REGEX_YOUTUBE_DOMAIN|$REGEX_YOUTUBE_SHORTER_DOMAIN)"
