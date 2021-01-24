package org.systers.mentorship.remote;

import java.lang.System;

/**
 * * Represents a custom HTTP requests interceptor
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lorg/systers/mentorship/remote/CustomInterceptor;", "Lokhttp3/Interceptor;", "()V", "preferenceManager", "Lorg/systers/mentorship/utils/PreferenceManager;", "getPreferenceManager", "()Lorg/systers/mentorship/utils/PreferenceManager;", "setPreferenceManager", "(Lorg/systers/mentorship/utils/PreferenceManager;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_debug"})
public final class CustomInterceptor implements okhttp3.Interceptor {
    @org.jetbrains.annotations.NotNull()
    private org.systers.mentorship.utils.PreferenceManager preferenceManager;
    
    @org.jetbrains.annotations.NotNull()
    public final org.systers.mentorship.utils.PreferenceManager getPreferenceManager() {
        return null;
    }
    
    public final void setPreferenceManager(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.utils.PreferenceManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    public CustomInterceptor() {
        super();
    }
}