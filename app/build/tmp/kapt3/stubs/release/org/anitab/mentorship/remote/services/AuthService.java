package org.anitab.mentorship.remote.services;

import java.lang.System;

/**
 * This interface describes the methods related to Authentication REST API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0002\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0006\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lorg/anitab/mentorship/remote/services/AuthService;", "", "login", "Lorg/anitab/mentorship/remote/responses/AuthToken;", "Lorg/anitab/mentorship/remote/requests/Login;", "(Lorg/anitab/mentorship/remote/requests/Login;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "Lorg/anitab/mentorship/remote/requests/Register;", "(Lorg/anitab/mentorship/remote/requests/Register;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface AuthService {
    
    /**
     * This function allows a user to login into the system
     * @param login data required to login a user
     * @return an observable instance of the [AuthToken]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "login")
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.remote.requests.Login login, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.AuthToken> p1);
    
    /**
     * This function allows a user to sign up into the system
     * @param register data required to register a user
     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "register")
    public abstract java.lang.Object register(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.remote.requests.Register register, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
}