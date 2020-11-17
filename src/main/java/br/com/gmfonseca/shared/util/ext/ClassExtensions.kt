package br.com.gmfonseca.shared.util.ext

/**
 * Created by Gabriel Fonseca on 07/11/2020.
 */
fun <T : Any> Class<T>.createInstance(): T? {
    return try {
        getDeclaredConstructor().newInstance()
    } catch (t: Throwable) {
        null
    }
}