package org.anitab.mentorship.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003\u001a\u0006\u0010\u0006\u001a\u00020\u0001\u00a8\u0006\u0007"}, d2 = {"convertJwtPayloadToObject", "Lorg/anitab/mentorship/remote/responses/JwtPayload;", "str", "", "decodeJwtPayload", "jwt", "getAuthTokenPayload", "app_release"})
public final class JwtUtilsKt {
    
    /**
     * Decodes a JSON Web Token's header and body
     * @param jwt JSON Web Token in string format
     * @return body/payload of jwt Base64 decoded
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String decodeJwtPayload(@org.jetbrains.annotations.NotNull()
    java.lang.String jwt) {
        return null;
    }
    
    /**
     * Converts the JWT payload response from string format to [JwtPayload]
     * @param str JSON Web Token payload in string format
     * @return [JwtPayload] holding the token decoded
     */
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.remote.responses.JwtPayload convertJwtPayloadToObject(@org.jetbrains.annotations.NotNull()
    java.lang.String str) {
        return null;
    }
    
    /**
     * Converts the JWT token of the current user to [JwtPayload]
     * @return [JwtPayload] holding the current's user token decoded
     */
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.remote.responses.JwtPayload getAuthTokenPayload() {
        return null;
    }
}