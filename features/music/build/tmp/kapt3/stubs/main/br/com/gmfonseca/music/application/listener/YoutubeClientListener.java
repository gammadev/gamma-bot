package br.com.gmfonseca.music.application.listener;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 25/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0016\u0010\n\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lbr/com/gmfonseca/music/application/listener/YoutubeClientListener;", "Lbr/com/gmfonseca/music/business/client/YoutubeClient$YoutubeClientListener;", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "(Lnet/dv8tion/jda/api/entities/TextChannel;)V", "onLoadFailed", "", "message", "", "onNoMatches", "onPlaylistLoaded", "tracks", "", "Lcom/sedmelluq/discord/lavaplayer/track/AudioTrack;", "onTrackLoaded", "track", "play", "guildId", "music"})
public final class YoutubeClientListener implements br.com.gmfonseca.music.business.client.YoutubeClient.YoutubeClientListener {
    private final net.dv8tion.jda.api.entities.TextChannel channel = null;
    
    @java.lang.Override
    public void onTrackLoaded(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.track.AudioTrack track) {
    }
    
    @java.lang.Override
    public void onPlaylistLoaded(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.sedmelluq.discord.lavaplayer.track.AudioTrack> tracks) {
    }
    
    @java.lang.Override
    public void onNoMatches() {
    }
    
    @java.lang.Override
    public void onLoadFailed(@org.jetbrains.annotations.Nullable
    java.lang.String message) {
    }
    
    private final void play(java.lang.String guildId, com.sedmelluq.discord.lavaplayer.track.AudioTrack track) {
    }
    
    public YoutubeClientListener(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel) {
        super();
    }
}