package org.systers.mentorship.remote.services;

import java.lang.System;

/**
 * * This interface describes the methods related to Users REST API
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\'J\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\u0003H\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\u0003H\'J*\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n0\u00032\u0014\b\u0001\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rH\'J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0006H\'\u00a8\u0006\u0015"}, d2 = {"Lorg/systers/mentorship/remote/services/UserService;", "", "getHomeStats", "Lio/reactivex/Observable;", "Lorg/systers/mentorship/models/HomeStatistics;", "getUser", "Lorg/systers/mentorship/models/User;", "userId", "", "getUsers", "", "getVerifiedUsers", "pagination", "", "", "updatePassword", "Lorg/systers/mentorship/remote/responses/CustomResponse;", "changePassword", "Lorg/systers/mentorship/remote/requests/ChangePassword;", "updateUser", "user", "app_debug"})
public abstract interface UserService {
    
    /**
     * * This function returns all users of the system
     *     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.User>> getUsers();
    
    /**
     * * This function returns all users, with email verified,
     *     * of the system with requested pagination info
     *     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/verified")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.User>> getVerifiedUsers(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.QueryMap()
    java.util.Map<java.lang.String, java.lang.String> pagination);
    
    /**
     * * This function returns all users, with email verified, of the system
     *     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/verified")
    public abstract io.reactivex.Observable<java.util.List<org.systers.mentorship.models.User>> getVerifiedUsers();
    
    /**
     * * This function returns a user's public profile of the system
     *     * @return an observable instance of the [User]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/{userId}")
    public abstract io.reactivex.Observable<org.systers.mentorship.models.User> getUser(@retrofit2.http.Path(value = "userId")
    int userId);
    
    /**
     * * This function returns the current user profile
     *     * @return an observable instance of the [User]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user")
    public abstract io.reactivex.Observable<org.systers.mentorship.models.User> getUser();
    
    /**
     * * This function updates the current user's profile
     *     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "user")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> updateUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.models.User user);
    
    /**
     * * This function updates the current user password
     *     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "user/change_password")
    public abstract io.reactivex.Observable<org.systers.mentorship.remote.responses.CustomResponse> updatePassword(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.systers.mentorship.remote.requests.ChangePassword changePassword);
    
    /**
     * * This function gets the current user's home screen statistics
     *     * @return an observable instance of [HomeStatistics]
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "home")
    public abstract io.reactivex.Observable<org.systers.mentorship.models.HomeStatistics> getHomeStats();
}