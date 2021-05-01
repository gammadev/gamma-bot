package br.com.gmfonseca.shared.util;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 02/10/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JV\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0012H\u0002JF\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0012J<\u0010\u0015\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0012JH\u0010\u0016\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0012R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lbr/com/gmfonseca/shared/util/EmbedMessage;", "", "()V", "ERROR", "Ljava/awt/Color;", "kotlin.jvm.PlatformType", "INFO", "SUCCESS", "buildAndQueue", "", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "color", "title", "", "description", "footer", "onMessageSent", "Lkotlin/Function1;", "Lnet/dv8tion/jda/api/entities/Message;", "failure", "info", "success", "music"})
public final class EmbedMessage {
    private static final java.awt.Color SUCCESS = null;
    private static final java.awt.Color INFO = null;
    private static final java.awt.Color ERROR = null;
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.shared.util.EmbedMessage INSTANCE = null;
    
    public final void success(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String footer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super net.dv8tion.jda.api.entities.Message, kotlin.Unit> onMessageSent) {
    }
    
    public final void info(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super net.dv8tion.jda.api.entities.Message, kotlin.Unit> onMessageSent) {
    }
    
    public final void failure(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String footer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super net.dv8tion.jda.api.entities.Message, kotlin.Unit> onMessageSent) {
    }
    
    private final void buildAndQueue(net.dv8tion.jda.api.entities.TextChannel channel, java.awt.Color color, java.lang.String title, java.lang.String description, java.lang.String footer, kotlin.jvm.functions.Function1<? super net.dv8tion.jda.api.entities.Message, kotlin.Unit> onMessageSent) {
    }
    
    private EmbedMessage() {
        super();
    }
}