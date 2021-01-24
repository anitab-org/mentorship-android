package org.systers.mentorship.utils;

import java.lang.System;

/**
 * * Object to store utilities such as a [Gson] instance
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lorg/systers/mentorship/utils/CommonUtils;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "getErrorResponse", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "throwable", "", "app_release"})
public final class CommonUtils {
    @org.jetbrains.annotations.NotNull()
    private static final com.google.gson.Gson gson = null;
    public static final org.systers.mentorship.utils.CommonUtils INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.gson.Gson getGson() {
        return null;
    }
    
    /**
     * * Extracts a CustomResponse object from a throwable
     *     * @param throwable from which the object is to be extracted
     *     * @return a CustomResponse object
     */
    @org.jetbrains.annotations.NotNull()
    public final org.systers.mentorship.remote.responses.CustomResponse getErrorResponse(@org.jetbrains.annotations.NotNull()
    @io.reactivex.annotations.NonNull()
    java.lang.Throwable throwable) {
        return null;
    }
    
    private CommonUtils() {
        super();
    }
}