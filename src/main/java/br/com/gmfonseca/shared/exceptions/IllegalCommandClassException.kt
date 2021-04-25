package br.com.gmfonseca.shared.exceptions

import br.com.gmfonseca.shared.command.CommandHandler
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

/**
 * Thrown by illegal command class declaration, that requires a
 * [CommandHandler] annotation
 */
class IllegalCommandClassException(val klass: KClass<*>) :
    Throwable("Missing CommandHandler annotation to your command class '${klass.jvmName}'")