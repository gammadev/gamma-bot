package br.com.gmfonseca.music.application.handler.audio;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lbr/com/gmfonseca/music/application/handler/audio/AudioSenderHandler;", "Lnet/dv8tion/jda/api/audio/AudioSendHandler;", "audioPlayer", "Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayer;", "(Lcom/sedmelluq/discord/lavaplayer/player/AudioPlayer;)V", "buffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "frame", "Lcom/sedmelluq/discord/lavaplayer/track/playback/MutableAudioFrame;", "canProvide", "", "isOpus", "provide20MsAudio", "music"})
public final class AudioSenderHandler implements net.dv8tion.jda.api.audio.AudioSendHandler {
    private final java.nio.ByteBuffer buffer = null;
    private final com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame frame = null;
    private final com.sedmelluq.discord.lavaplayer.player.AudioPlayer audioPlayer = null;
    
    @java.lang.Override
    public boolean canProvide() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.nio.ByteBuffer provide20MsAudio() {
        return null;
    }
    
    @java.lang.Override
    public boolean isOpus() {
        return false;
    }
    
    public AudioSenderHandler(@org.jetbrains.annotations.NotNull
    com.sedmelluq.discord.lavaplayer.player.AudioPlayer audioPlayer) {
        super();
    }
}