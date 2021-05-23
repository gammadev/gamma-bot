package br.com.gmfonseca.bot.commandmanager

import kotlin.reflect.KClass


fun <T : Any, A : Annotation> T.getAnnotation(annotationClass: KClass<A>): A? {
    return this::class.java.getAnnotationsByType(annotationClass.java).firstOrNull()
}
