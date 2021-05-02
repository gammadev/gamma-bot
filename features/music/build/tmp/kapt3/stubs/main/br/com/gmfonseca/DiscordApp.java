package br.com.gmfonseca;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J!\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0012\"\u00020\u0013H\u0002\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\rJ\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0018J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rJ\u0018\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0002J\u001b\u0010\u001d\u001a\u00020\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\u0007\u00a2\u0006\u0002\u0010\u001fJ5\u0010 \u001a\u00020\u0010\"\u0006\b\u0000\u0010!\u0018\u00012\b\b\u0002\u0010\"\u001a\u00020\r2\u0018\u0010#\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0%\u0012\u0004\u0012\u00020\u00100$H\u0082\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lbr/com/gmfonseca/DiscordApp;", "", "()V", "COMMAND_PREFIX", "", "INSTANCE", "Lnet/dv8tion/jda/api/JDA;", "PLAYER_MANAGER", "Lcom/sedmelluq/discord/lavaplayer/player/DefaultAudioPlayerManager;", "getPLAYER_MANAGER", "()Lcom/sedmelluq/discord/lavaplayer/player/DefaultAudioPlayerManager;", "guildsMusicManager", "", "", "Lbr/com/gmfonseca/music/business/manager/GuildMusicManager;", "addEventListener", "", "listeners", "", "Lnet/dv8tion/jda/api/hooks/ListenerAdapter;", "([Lnet/dv8tion/jda/api/hooks/ListenerAdapter;)V", "clearMusicManager", "guildId", "getMusicManager", "", "logSevere", "throwable", "", "message", "main", "args", "([Ljava/lang/String;)V", "mapClasses", "T", "classesRootPath", "onFinish", "Lkotlin/Function1;", "", "music"})
public final class DiscordApp {
    public static final char COMMAND_PREFIX = '>';
    @org.jetbrains.annotations.NotNull
    private static final com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager PLAYER_MANAGER = null;
    private static final java.util.Map<java.lang.String, br.com.gmfonseca.music.business.manager.GuildMusicManager> guildsMusicManager = null;
    private static net.dv8tion.jda.api.JDA INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.DiscordApp INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager getPLAYER_MANAGER() {
        return null;
    }
    
    public static final void main(@org.jetbrains.annotations.NotNull
    java.lang.String[] args) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final br.com.gmfonseca.music.business.manager.GuildMusicManager getMusicManager(@org.jetbrains.annotations.NotNull
    java.lang.String guildId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final br.com.gmfonseca.music.business.manager.GuildMusicManager getMusicManager(long guildId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final br.com.gmfonseca.music.business.manager.GuildMusicManager clearMusicManager(@org.jetbrains.annotations.NotNull
    java.lang.String guildId) {
        return null;
    }
    
    private final void addEventListener(net.dv8tion.jda.api.hooks.ListenerAdapter... listeners) {
    }
    
    private final void logSevere(java.lang.Throwable throwable, java.lang.String message) {
    }
    
    private DiscordApp() {
        super();
    }
}