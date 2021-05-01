package br.com.gmfonseca.shared;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"REGEX_FLOAT_ONLY", "", "REGEX_INTEGER_ONLY", "REGEX_PROTOCOL", "REGEX_YOUTUBE", "REGEX_YOUTUBE_DOMAIN", "REGEX_YOUTUBE_SHORTER_DOMAIN", "music"})
public final class AppConstantsKt {
    
    /**
     * Created by Gabriel Fonseca on 05/11/2020.
     */
    private static final java.lang.String REGEX_PROTOCOL = "(?:(http)(|s)://)";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REGEX_INTEGER_ONLY = "(?:[0-9]+)";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REGEX_FLOAT_ONLY = "(?:([0-9]+|)\\.([0-9]+|))";
    private static final java.lang.String REGEX_YOUTUBE_DOMAIN = "(?:(www.|m.|music.|)youtube.com)/(watch|playlist)\\?[a-zA-Z0-9_\\-&=\\.]+";
    private static final java.lang.String REGEX_YOUTUBE_SHORTER_DOMAIN = "(?:youtu.be/[a-zA-Z0-9_\\-&=.]+)";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REGEX_YOUTUBE = "(?:(http)(|s)://)((?:(www.|m.|music.|)youtube.com)/(watch|playlist)\\?[a-zA-Z0-9_\\-&=\\.]+|(?:youtu.be/[a-zA-Z0-9_\\-&=.]+))";
}