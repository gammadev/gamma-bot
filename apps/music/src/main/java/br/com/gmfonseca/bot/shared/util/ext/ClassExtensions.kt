package br.com.gmfonseca.bot.shared.util.ext

/**
 * Created by Gabriel Fonseca on 07/11/2020.
 */
fun <T : Any> Class<T>.createInstance(): T? {
    return try {
        getDeclaredConstructor().newInstance()
    } catch (e: IllegalAccessException) {
        null
    }
}