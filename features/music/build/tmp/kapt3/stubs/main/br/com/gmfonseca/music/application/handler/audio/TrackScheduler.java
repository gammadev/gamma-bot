package br.com.gmfonseca.music.application.handler.audio;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 23/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001+B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0006J\b\u0010\u001d\u001a\u00020\u0018H\u0002J \u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020!H\u0016J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\bJ\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010(\u001a\u00020\u0018J\u0012\u0010)\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u000e\u0010*\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006,"}, d2 = {"Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler;", "Lcom/sedmelluq/discord/lavaplayer/player/event/AudioEventAdapter;", "player", "Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayer;", "(Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayer;)V", "curIndex", "", "curTrack", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrack;", "isStopped", "", "value", "Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler$ITrackSchedulerListener;", "listener", "getListener", "()Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler$ITrackSchedulerListener;", "setListener", "(Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler$ITrackSchedulerListener;)V", "tracks", "", "volume", "getVolume", "()I", "clearQueue", "", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "jump", "index", "nextTrack", "onTrackEnd", "track", "endReason", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrackEndReason;", "pause", "playTrackAt", "queue", "queueToString", "", "resume", "skip", "stop", "switchVolume", "ITrackSchedulerListener", "music"})
public final class TrackScheduler extends com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter {
    @org.jetbrains.annotations.Nullable
    private br.com.gmfonseca.music.application.handler.audio.TrackScheduler.ITrackSchedulerListener listener;
    private final java.util.List<com.sedmelluq.discord.lavaplayer.track.AudioTrack> tracks = null;
    private int curIndex = -1;
    private com.sedmelluq.discord.lavaplayer.track.AudioTrack curTrack;
    private boolean isStopped = false;
    private final com.sedmelluq.discord.lavaplayer.player.AudioPlayer player = null;
    
    public final int getVolume() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final br.com.gmfonseca.music.application.handler.audio.TrackScheduler.ITrackSchedulerListener getListener() {
        return null;
    }
    
    public final void setListener(@org.jetbrains.annotations.Nullable
    br.com.gmfonseca.music.application.handler.audio.TrackScheduler.ITrackSchedulerListener value) {
    }
    
    @java.lang.Override
    public void onTrackEnd(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.player.AudioPlayer player, @org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.track.AudioTrack track, @org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason endReason) {
    }
    
    public final void clearQueue(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel) {
    }
    
    public final boolean switchVolume(int volume) {
        return false;
    }
    
    public final void queue(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.track.AudioTrack track) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String queueToString() {
        return null;
    }
    
    public final void resume(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel) {
    }
    
    public final void pause(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel) {
    }
    
    public final void stop(@org.jetbrains.annotations.Nullable
    net.dv8tion.jda.api.entities.TextChannel channel) {
    }
    
    public final void jump(int index) {
    }
    
    public final void skip() {
    }
    
    private final void nextTrack() {
    }
    
    private final void playTrackAt(int index) {
    }
    
    public TrackScheduler(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.player.AudioPlayer player) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lbr/com/gmfonseca/music/application/handler/audio/TrackScheduler$ITrackSchedulerListener;", "", "onFinish", "", "onNextTrack", "track", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrack;", "onWrongIndex", "index", "", "music"})
    public static abstract interface ITrackSchedulerListener {
        
        public abstract void onNextTrack(@org.jetbrains.annotations.NotNull
        com.sedmelluq.discord.lavaplayer.track.AudioTrack track);
        
        public abstract void onWrongIndex(int index);
        
        public abstract void onFinish();
    }
}