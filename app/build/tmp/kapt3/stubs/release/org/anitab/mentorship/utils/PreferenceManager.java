package org.anitab.mentorship.utils;

import java.lang.System;

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/anitab/mentorship/utils/PreferenceManager;", "", "()V", "authToken", "", "getAuthToken", "()Ljava/lang/String;", "context", "Landroid/content/Context;", "sharedPreferences", "Landroid/content/SharedPreferences;", "clear", "", "putAuthToken", "Companion", "app_release"})
public final class PreferenceManager {
    private final android.content.Context context = null;
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APPLICATION_PREFERENCE = "app-preferences";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTH_TOKEN = "auth-token";
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.utils.PreferenceManager.Companion Companion = null;
    
    /**
     * Saves the authorization token to SharedPreferences file.
     * @param authToken String which is the authorization token
     */
    @android.annotation.SuppressLint(value = {"ApplySharedPref"})
    public final void putAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String authToken) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthToken() {
        return null;
    }
    
    /**
     * Clears all the data that has been saved in the preferences file.
     */
    public final void clear() {
    }
    
    public PreferenceManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/anitab/mentorship/utils/PreferenceManager$Companion;", "", "()V", "APPLICATION_PREFERENCE", "", "AUTH_TOKEN", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}