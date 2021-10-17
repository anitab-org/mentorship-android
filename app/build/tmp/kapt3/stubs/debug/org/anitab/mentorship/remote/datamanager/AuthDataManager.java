package org.anitab.mentorship.remote.datamanager;

import java.lang.System;

/**
 * This class represents the data manager related to Authentication API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/anitab/mentorship/remote/datamanager/AuthDataManager;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "apiManager", "Lorg/anitab/mentorship/remote/ApiManager;", "login", "Lorg/anitab/mentorship/remote/responses/AuthToken;", "Lorg/anitab/mentorship/remote/requests/Login;", "(Lorg/anitab/mentorship/remote/requests/Login;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "Lorg/anitab/mentorship/remote/requests/Register;", "(Lorg/anitab/mentorship/remote/requests/Register;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuthDataManager {
    private final org.anitab.mentorship.remote.ApiManager apiManager = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    /**
     * This will call the login method of AuthService interface
     * @param login The login request body containing the credentials
     * @return an Observable AuthToken
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.Login login, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.AuthToken> p1) {
        return null;
    }
    
    /**
     * This will call the register method of AuthService interface
     * @param register The registration request body containing
     *                       the registration required fields
     * @return an Observable CustomResponse
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.Register register, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    public AuthDataManager(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    public AuthDataManager() {
        super();
    }
}