package br.com.gmfonseca.bot.core.data

interface CacheableRepository<in Key, out Value> {
    operator fun get(key: Key): Value

    fun remove(key: Key): Value?
}
