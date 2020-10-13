package br.com.gmfonseca.utils.ext

import kotlin.reflect.KClass

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
@Suppress("UNUSED")
fun <T : Any> KClass<T>.createInstance(): T {
    return java.getConstructor().newInstance()
}