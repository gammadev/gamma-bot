package br.com.gmfonseca.music.business.client;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lbr/com/gmfonseca/music/business/client/YoutubeClient;", "", "listener", "Lbr/com/gmfonseca/music/business/client/YoutubeClient$YoutubeClientListener;", "(Lbr/com/gmfonseca/music/business/client/YoutubeClient$YoutubeClientListener;)V", "download", "", "identifier", "", "search", "YoutubeClientListener", "music"})
public final class YoutubeClient {
    private final br.com.gmfonseca.music.business.client.YoutubeClient.YoutubeClientListener listener = null;
    
    public final void search(@org.jetbrains.annotations.NotNull
    java.lang.String identifier) {
    }
    
    public final void download(@org.jetbrains.annotations.NotNull
    java.lang.String identifier) {
    }
    
    public YoutubeClient(@org.jetbrains.annotations.NotNull
    br.com.gmfonseca.music.business.client.YoutubeClient.YoutubeClientListener listener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH&\u00a8\u0006\r"}, d2 = {"Lbr/com/gmfonseca/music/business/client/YoutubeClient$YoutubeClientListener;", "", "onLoadFailed", "", "message", "", "onNoMatches", "onPlaylistLoaded", "tracks", "", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrack;", "onTrackLoaded", "track", "music"})
    public static abstract interface YoutubeClientListener {
        
        public abstract void onTrackLoaded(@org.jetbrains.annotations.NotNull
        com.sedmelluq.discord.lavaplayer.track.AudioTrack track);
        
        public abstract void onPlaylistLoaded(@org.jetbrains.annotations.NotNull
        java.util.List<? extends com.sedmelluq.discord.lavaplayer.track.AudioTrack> tracks);
        
        public abstract void onNoMatches();
        
        public abstract void onLoadFailed(@org.jetbrains.annotations.Nullable
        java.lang.String message);
    }
}