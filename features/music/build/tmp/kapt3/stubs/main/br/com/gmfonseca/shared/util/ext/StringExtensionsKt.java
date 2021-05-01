package br.com.gmfonseca.shared.util.ext;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u00002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\u0004\u001a&\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0002\u001a\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r*\u00020\u0002\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001a\u0010\u0012\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0002\u001a\u0012\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a(\u0010\u0016\u001a\u00020\u0002*\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u00a8\u0006\u0017"}, d2 = {"equalsIgnoreCase", "", "", "other", "fill", "maxLength", "", "fillStart", "fillChar", "", "getCommand", "Lbr/com/gmfonseca/shared/command/Command;", "getCommandArgs", "", "isCommand", "mapFileToClassPath", "file", "Ljava/io/File;", "substringBetween", "after", "before", "truncate", "truncateOrFill", "music"})
public final class StringExtensionsKt {
    
    /**
     * Created by Gabriel Fonseca on 18/09/2020.
     */
    public static final boolean isCommand(@org.jetbrains.annotations.NotNull
    java.lang.String $this$isCommand) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final br.com.gmfonseca.shared.command.Command getCommand(@org.jetbrains.annotations.NotNull
    java.lang.String $this$getCommand) throws java.lang.StringIndexOutOfBoundsException {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.String> getCommandArgs(@org.jetbrains.annotations.NotNull
    java.lang.String $this$getCommandArgs) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String mapFileToClassPath(@org.jetbrains.annotations.NotNull
    java.lang.String $this$mapFileToClassPath, @org.jetbrains.annotations.NotNull
    java.io.File file) {
        return null;
    }
    
    /**
     * Fill [this] with [fillChar] to reach to [maxLength], or returns [this] if bigger than [maxLength]
     *
     * The flag [fillStart] validates if should fill with [fillChar] before [this].
     * @Example if [fillStart] then returns "    this" else "this    "
     *
     * @param maxLength the bigger allowed length for [this]
     * @param fillStart specifies where to fill
     * @param fillChar value to fill
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String fill(@org.jetbrains.annotations.NotNull
    java.lang.String $this$fill, int maxLength, boolean fillStart, char fillChar) {
        return null;
    }
    
    /**
     * Truncate string based on [maxLength], where if [this] is bigger than [maxLength],
     * then replaces character at {[maxLength]-1} with '…' and return. Otherwise returns [this].
     *
     * @param maxLength the bigger allowed length for [this]
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String truncate(@org.jetbrains.annotations.NotNull
    java.lang.String $this$truncate, int maxLength) {
        return null;
    }
    
    /**
     * Truncate [this] if bigger than [maxLength]. Otherwise, fill with [fillChar] by [fillStart].
     *
     * @see String.truncate to check how truncate works
     * @see String.fill to check how fill works
     *
     * @param maxLength the bigger allowed length for [this]
     * @param fillStart specifies where to fill
     * @param fillChar value to fill
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String truncateOrFill(@org.jetbrains.annotations.Nullable
    java.lang.String $this$truncateOrFill, int maxLength, boolean fillStart, char fillChar) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String substringBetween(@org.jetbrains.annotations.NotNull
    java.lang.String $this$substringBetween, @org.jetbrains.annotations.NotNull
    java.lang.String after, @org.jetbrains.annotations.NotNull
    java.lang.String before) {
        return null;
    }
    
    public static final boolean equalsIgnoreCase(@org.jetbrains.annotations.NotNull
    java.lang.String $this$equalsIgnoreCase, @org.jetbrains.annotations.NotNull
    java.lang.String other) {
        return false;
    }
}