package br.com.gmfonseca.shared.utils.ext

/**
 * Created by Gabriel Fonseca on 07/11/2020.
 */
fun <T : Any> Class<T>.createInstance(): T {
    return getDeclaredConstructor().newInstance()
}