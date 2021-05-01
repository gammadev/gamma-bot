package br.com.gmfonseca.shared.command;

import java.lang.System;

/**
 * Created by Gabriel Fonseca on 18/09/2020.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0005H\u0004J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lbr/com/gmfonseca/shared/command/Command;", "", "()V", "aliases", "", "", "name", "getName", "()Ljava/lang/String;", "onCommand", "", "message", "Lnet/dv8tion/jda/api/entities/Message;", "channel", "Lnet/dv8tion/jda/api/entities/TextChannel;", "args", "onWrongCommand", "", "extra", "toString", "Companion", "music"})
public abstract class Command {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    private final java.util.List<java.lang.String> aliases = null;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<? extends br.com.gmfonseca.shared.command.Command> commands;
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.shared.command.Command.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public abstract boolean onCommand(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.Message message, @org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> args);
    
    protected final void onWrongCommand(@org.jetbrains.annotations.NotNull
    net.dv8tion.jda.api.entities.TextChannel channel, @org.jetbrains.annotations.NotNull
    java.lang.String extra) {
    }
    
    public Command() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R0\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lbr/com/gmfonseca/shared/command/Command$Companion;", "", "()V", "<set-?>", "", "Lbr/com/gmfonseca/shared/command/Command;", "commands", "getCommands", "()Ljava/util/List;", "setCommands", "(Ljava/util/List;)V", "findCommand", "name", "", "loadCommands", "", "music"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<br.com.gmfonseca.shared.command.Command> getCommands() {
            return null;
        }
        
        private final void setCommands(java.util.List<? extends br.com.gmfonseca.shared.command.Command> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final br.com.gmfonseca.shared.command.Command findCommand(@org.jetbrains.annotations.NotNull
        java.lang.String name) {
            return null;
        }
        
        public final void loadCommands(@org.jetbrains.annotations.NotNull
        java.util.List<? extends br.com.gmfonseca.shared.command.Command> commands) {
        }
        
        private Companion() {
            super();
        }
    }
}