package org.systers.mentorship.remote.datamanager;

import java.lang.System;

/**
 * * This class represents the data manager related to Users API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\u0006J\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00062\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00062\u0006\u0010\u0015\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "", "()V", "apiManager", "Lorg/systers/mentorship/remote/ApiManager;", "getHomeStats", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/models/HomeStatistics;", "getUser", "Lorg/systers/mentorship/models/User;", "userId", "", "getUsers", "", "paginationRequest", "Lorg/systers/mentorship/remote/requests/PaginationRequest;", "updatePassword", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "changePassword", "Lorg/systers/mentorship/remote/requests/ChangePassword;", "updateUser", "user", "app_debug"})
public final class UserDataManager {
    private final org.systers.mentorship.remote.ApiManager apiManager = null;
    
    /**
     * * This will call the getVerifiedUsers method of UserService interface
     *     * @return an Observable of a list of [User]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.User>> getUsers() {
        return null;
    }
    
    /**
     * * This will call the getVerifiedUsers(pagination) method of UserService interface
     *     * @return an Observable of a list of [User]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<org.systers.mentorship.models.User>> getUsers(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.PaginationRequest paginationRequest) {
        return null;
    }
    
    /**
     * * This will call the getUser method of UserService interface
     *     * @return an Observable of [User]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.models.User> getUser(int userId) {
        return null;
    }
    
    /**
     * * This will call the getUser method of UserService interface
     *     * @return an Observable of [User]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.models.User> getUser() {
        return null;
    }
    
    /**
     * * This will call the updateUser method of UserService interface
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> updateUser(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.User user) {
        return null;
    }
    
    /**
     * * This will call the updatePassword method of UserService interface
     *     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> updatePassword(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.remote.requests.ChangePassword changePassword) {
        return null;
    }
    
    /**
     * * This function fetches user statistics
     *     * @return an observable of [HomeStatistics]
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<org.systers.mentorship.models.HomeStatistics> getHomeStats() {
        return null;
    }
    
    public UserDataManager() {
        super();
    }
}