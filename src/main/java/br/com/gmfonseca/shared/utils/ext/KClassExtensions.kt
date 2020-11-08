package br.com.gmfonseca.shared.utils.ext

import kotlin.reflect.KClass

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
fun <T : Any, A : Annotation> T.getAnnotation(annotationClass: KClass<A>): A? {
    return this::class.java.getAnnotationsByType(annotationClass.java).firstOrNull()
}
