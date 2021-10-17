package org.anitab.mentorship.remote.responses;

import java.lang.System;

/**
 * Response encoded in JSON Web Token, holding the token information and the current's user identity
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001J\t\u0010$\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016\u00a8\u0006%"}, d2 = {"Lorg/anitab/mentorship/remote/responses/JwtPayload;", "", "identity", "", "iat", "", "nbf", "jti", "", "exp", "fresh", "", "type", "(IJJLjava/lang/String;JZLjava/lang/String;)V", "getExp", "()J", "getFresh", "()Z", "getIat", "getIdentity", "()I", "getJti", "()Ljava/lang/String;", "getNbf", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class JwtPayload {
    private final int identity = 0;
    private final long iat = 0L;
    private final long nbf = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jti = null;
    private final long exp = 0L;
    private final boolean fresh = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    
    public final int getIdentity() {
        return 0;
    }
    
    public final long getIat() {
        return 0L;
    }
    
    public final long getNbf() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJti() {
        return null;
    }
    
    public final long getExp() {
        return 0L;
    }
    
    public final boolean getFresh() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    public JwtPayload(int identity, long iat, long nbf, @org.jetbrains.annotations.NotNull()
    java.lang.String jti, long exp, boolean fresh, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    /**
     * Response encoded in JSON Web Token, holding the token information and the current's user identity
     */
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.remote.responses.JwtPayload copy(int identity, long iat, long nbf, @org.jetbrains.annotations.NotNull()
    java.lang.String jti, long exp, boolean fresh, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    /**
     * Response encoded in JSON Web Token, holding the token information and the current's user identity
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * Response encoded in JSON Web Token, holding the token information and the current's user identity
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Response encoded in JSON Web Token, holding the token information and the current's user identity
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}