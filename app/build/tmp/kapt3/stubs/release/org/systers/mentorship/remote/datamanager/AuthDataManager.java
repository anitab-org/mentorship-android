package org.systers.mentorship.remote.datamanager;

import java.lang.System;

/**
 * * This class represents the data manager related to Authentication API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\t\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/systers/mentorship/remote/datamanager/AuthDataManager;", "", "()V", "apiManager", "Lorg/systers/mentorship/remote/ApiManager;", "login", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/remote/responses/AuthToken;", "Lorg/systers/mentorship/remote/requests/Login;", "register", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "Lorg/systers/mentorship/remote/requests/Register;", "app_release"})
public final class AuthDataManager {
    private final org.systers.mentorship.remote.ApiManager apiManager = null;
    
    /**
     * * This will call the login method of AuthService interface
     *     * @param login The login request body containing the credentials
     *     * @return an Observable AuthToken
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.AuthToken> login(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.Login login) {
        return null;
    }
    
    /**
     * * This will call the register method of AuthService interface
     *     * @param register The registration request body containing
     *     *                        the registration required fields
     *     * @return an Observable CustomResponse
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> register(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.Register register) {
        return null;
    }
    
    public AuthDataManager() {
        super();
    }
}