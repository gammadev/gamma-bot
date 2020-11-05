package br.com.gmfonseca.shared.utils.ext

import kotlin.random.Random

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
fun <T> List<T>.randomize(): List<T> {

    return if (size <= 1) {
        this
    } else {
        val mutableCopy = toMutableList()
        val mutable = mutableListOf<T>()

        while (mutableCopy.isNotEmpty()) {
            when (mutableCopy.size) {
                1 -> mutable.add(mutableCopy.removeAt(0))
                else -> mutable.add(mutableCopy.removeAt(Random.nextInt(0, mutable.size)))
            }
        }

        mutable
    }
}