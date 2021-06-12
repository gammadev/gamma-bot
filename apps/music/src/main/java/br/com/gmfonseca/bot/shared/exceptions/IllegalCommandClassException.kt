package br.com.gmfonseca.bot.shared.exceptions

import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

/**
 * Thrown by illegal command class declaration, that requires a
 * [br.com.gmfonseca.annotations.CommandHandler] annotation
 */
class IllegalCommandClassException(val klass: KClass<*>) :
    Throwable("Missing CommandHandler annotation to your command class '${klass.jvmName}'")
