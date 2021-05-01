package br.com.gmfonseca.music.business.manager;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lbr/com/gmfonseca/music/business/manager/GuildMusicManager;", "", "manager", "Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayerManager;", "(Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayerManager;)V", "audioSenderHandler", "Lbr/com/gmfonseca/music/application/handler/audio/AudioSenderHandler;", "getAudioSenderHandler", "()Lbr/com/gmfonseca/music/application/handler/audio/AudioSenderHandler;", "player", "Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayer;", "kotlin.jvm.PlatformType", "scheduler", "Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler;", "getScheduler", "()Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler;", "voiceChannel", "Lnet/dv8tion/jda/api/entities/VoiceChannel;", "getVoiceChannel", "()Lnet/dv8tion/jda/api/entities/VoiceChannel;", "setVoiceChannel", "(Lnet/dv8tion/jda/api/entities/VoiceChannel;)V", "music"})
public final class GuildMusicManager {
    private final com.sedmelluq.discord.lavaplayer.player.AudioPlayer player = null;
    @org.jetbrains.annotations.NotNull
    private final br.com.gmfonseca.music.application.handler.audio.TrackScheduler scheduler = null;
    @org.jetbrains.annotations.NotNull
    private final br.com.gmfonseca.music.application.handler.audio.AudioSenderHandler audioSenderHandler = null;
    @org.jetbrains.annotations.Nullable
    private net.dv8tion.jda.api.entities.VoiceChannel voiceChannel;
    
    @org.jetbrains.annotations.NotNull
    public final br.com.gmfonseca.music.application.handler.audio.TrackScheduler getScheduler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final br.com.gmfonseca.music.application.handler.audio.AudioSenderHandler getAudioSenderHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final net.dv8tion.jda.api.entities.VoiceChannel getVoiceChannel() {
        return null;
    }
    
    public final void setVoiceChannel(@org.jetbrains.annotations.Nullable
    net.dv8tion.jda.api.entities.VoiceChannel p0) {
    }
    
    public GuildMusicManager(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager manager) {
        super();
    }
}