package br.com.gmfonseca.shared.exceptions;

import java.lang.System;

/**
 * Thrown by illegal command class declaration, that requires a
 * [CommandHandler] annotation
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u00a2\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lbr/com/gmfonseca/shared/exceptions/IllegalCommandClassException;", "", "klass", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "getKlass", "()Lkotlin/reflect/KClass;", "music"})
public final class IllegalCommandClassException extends java.lang.Throwable {
    @org.jetbrains.annotations.NotNull
    private final kotlin.reflect.KClass<?> klass = null;
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.reflect.KClass<?> getKlass() {
        return null;
    }
    
    public IllegalCommandClassException(@org.jetbrains.annotations.NotNull
    kotlin.reflect.KClass<?> klass) {
        super(null);
    }
}