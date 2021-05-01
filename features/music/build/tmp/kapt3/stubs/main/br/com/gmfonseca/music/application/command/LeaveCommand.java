package br.com.gmfonseca.music.application.command;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 04/10/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016\u00a8\u0006\f"}, d2 = {"Lbr/com/gmfonseca/music/application/command/LeaveCommand;", "Lbr/com/gmfonseca/shared/command/Command;", "()V", "onCommand", "", "message", "Lnet/dv8tion/jda/api/entities/Message;", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "args", "", "", "music"})
@br.com.gmfonseca.annotations.CommandHandler(name = "leave", aliases = {"quit"})
public final class LeaveCommand extends br.com.gmfonseca.shared.command.Command {
    
    @java.lang.Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.Message message, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> args) {
        return false;
    }
    
    public LeaveCommand() {
        super();
    }
}