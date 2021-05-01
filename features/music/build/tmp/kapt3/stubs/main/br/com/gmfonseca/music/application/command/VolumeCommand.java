package br.com.gmfonseca.music.application.command;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J&\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u00a8\u0006\u0011"}, d2 = {"Lbr/com/gmfonseca/music/application/command/VolumeCommand;", "Lbr/com/gmfonseca/shared/command/Command;", "()V", "onCommand", "", "message", "Lnet/dv8tion/jda/api/entities/Message;", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "args", "", "", "onWrongCommand", "sendMessage", "title", "description", "Companion", "music"})
@br.com.gmfonseca.annotations.CommandHandler(name = "volume", aliases = {"v"})
public final class VolumeCommand extends br.com.gmfonseca.shared.command.Command {
    private static final java.util.Map<java.lang.Long, java.lang.Long> LAST_MESSAGE_ID = null;
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.music.application.command.VolumeCommand.Companion Companion = null;
    
    @java.lang.Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.Message message, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> args) {
        return false;
    }
    
    private final boolean sendMessage(java.lang.String title, java.lang.String description, net.dv8tion.jda.api.entities.TextChannel channel) {
        return false;
    }
    
    private final boolean onWrongCommand(net.dv8tion.jda.api.entities.TextChannel channel) {
        return false;
    }
    
    public VolumeCommand() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lbr/com/gmfonseca/music/application/command/VolumeCommand$Companion;", "", "()V", "LAST_MESSAGE_ID", "", "", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}