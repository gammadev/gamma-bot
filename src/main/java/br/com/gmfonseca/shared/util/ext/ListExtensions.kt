package br.com.gmfonseca.shared.util.ext

import kotlin.random.Random

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
fun <T> List<T>.randomize(): List<T> {

    return if (size <= 1) {
        this
    } else {
        val mutableCopy = toMutableList()
        val list = mutableListOf<T>()

        while (mutableCopy.isNotEmpty()) {
            when (mutableCopy.size) {
                1 -> list.add(mutableCopy.removeAt(0))
                else -> list.add(mutableCopy.removeAt(Random.nextInt(0, list.size)))
            }
        }

        list
    }
}

fun <T> MutableList<T>.addIfAbsent(element: T): Boolean {
    return if (!contains(element)) {
        add(element)
        true
    } else {
        false
    }
}