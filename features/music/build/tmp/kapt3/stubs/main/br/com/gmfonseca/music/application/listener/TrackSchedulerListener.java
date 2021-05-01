package br.com.gmfonseca.music.application.listener;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 27/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lbr/com/gmfonseca/music/application/listener/TrackSchedulerListener;", "Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler$ITrackSchedulerListener;", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "(Lnet/dv8tion/jda/api/entities/TextChannel;)V", "getChannel", "()Lnet/dv8tion/jda/api/entities/TextChannel;", "onFinish", "", "onNextTrack", "track", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrack;", "onWrongIndex", "index", "", "Companion", "music"})
public final class TrackSchedulerListener implements br.com.gmfonseca.music.application.handler.audio.TrackScheduler.ITrackSchedulerListener {
    @org.jetbrains.annotations.NotNull
    private final net.dv8tion.jda.api.entities.TextChannel channel = null;
    private static final java.util.Map<java.lang.Long, java.lang.Long> LAST_MESSAGE_ID = null;
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.music.application.listener.TrackSchedulerListener.Companion Companion = null;
    
    @java.lang.Override
    public void onNextTrack(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.track.AudioTrack track) {
    }
    
    @java.lang.Override
    public void onWrongIndex(int index) {
    }
    
    @java.lang.Override
    public void onFinish() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final net.dv8tion.jda.api.entities.TextChannel getChannel() {
        return null;
    }
    
    public TrackSchedulerListener(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lbr/com/gmfonseca/music/application/listener/TrackSchedulerListener$Companion;", "", "()V", "LAST_MESSAGE_ID", "", "", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}