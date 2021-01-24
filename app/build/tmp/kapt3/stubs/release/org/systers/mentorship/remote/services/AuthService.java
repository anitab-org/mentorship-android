package org.systers.mentorship.remote.services;

import java.lang.System;

/**
 * * This interface describes the methods related to Authentication REST API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0002\u001a\u00020\u0005H\'J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\u0006\u001a\u00020\bH\'\u00a8\u0006\t"}, d2 = {"Lorg/systers/mentorship/remote/services/AuthService;", "", "login", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/AuthToken;", "Lorg/systers/mentorship/remote/requests/Login;", "register", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "Lorg/systers/mentorship/remote/requests/Register;", "app_release"})
public abstract interface AuthService {
    
    /**
     * * This function allows a user to login into the system
     *     * @param login data required to login a user
     *     * @return an observable instance of the [AuthToken]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "login")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.AuthToken> login(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.remote.requests.Login login);
    
    /**
     * * This function allows a user to sign up into the system
     *     * @param register data required to register a user
     *     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "register")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> register(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.remote.requests.Register register);
}