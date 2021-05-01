package br.com.gmfonseca.shared.util.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\"\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a8\u0006\f"}, d2 = {"connectVoice", "", "Lnet/dv8tion/jda/api/managers/AudioManager;", "textChannel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "voiceChannel", "Lnet/dv8tion/jda/api/entities/VoiceChannel;", "safetyConnectVoice", "channel", "onInsufficientPermission", "Lkotlin/Function0;", "", "music"})
public final class AudioManagerExtensionsKt {
    
    /**
     * Created by Gabriel Fonseca on 02/12/2020.
     */
    public static final boolean connectVoice(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.managers.AudioManager $this$connectVoice, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel textChannel, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.VoiceChannel voiceChannel) {
        return false;
    }
    
    public static final boolean safetyConnectVoice(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.managers.AudioManager $this$safetyConnectVoice, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.VoiceChannel channel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onInsufficientPermission) {
        return false;
    }
}