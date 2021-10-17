package org.anitab.mentorship.remote.services;

import java.lang.System;

/**
 * This interface describes the methods related to Users REST API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J-\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0014\b\u0001\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00122\b\b\u0001\u0010\u0017\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/anitab/mentorship/remote/services/UserService;", "", "getHomeStats", "Lorg/anitab/mentorship/models/HomeStatistics;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "Lorg/anitab/mentorship/models/User;", "userId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsers", "", "getVerifiedUsers", "pagination", "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassword", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "changePassword", "Lorg/anitab/mentorship/remote/requests/ChangePassword;", "(Lorg/anitab/mentorship/remote/requests/ChangePassword;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "user", "(Lorg/anitab/mentorship/models/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserService {
    
    /**
     * This function returns all users of the system
     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "users")
    public abstract java.lang.Object getUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.User>> p0);
    
    /**
     * This function returns all users, with email verified,
     * of the system with requested pagination info
     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "users/verified")
    public abstract java.lang.Object getVerifiedUsers(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.QueryMap()
    java.util.Map<java.lang.String, java.lang.String> pagination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.User>> p1);
    
    /**
     * This function returns all users, with email verified, of the system
     * @return an observable instance of a list of [User]s
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "users/verified")
    public abstract java.lang.Object getVerifiedUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.User>> p0);
    
    /**
     * This function returns a user's public profile of the system
     * @return an observable instance of the [User]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "users/{userId}")
    public abstract java.lang.Object getUser(@retrofit2.http.Path(value = "userId")
    int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.User> p1);
    
    /**
     * This function returns the current user profile
     * @return an observable instance of the [User]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "user")
    public abstract java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.User> p0);
    
    /**
     * This function updates the current user's profile
     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "user")
    public abstract java.lang.Object updateUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.models.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function updates the current user password
     * @return an observable instance of the [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.PUT(value = "user/change_password")
    public abstract java.lang.Object updatePassword(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    org.anitab.mentorship.remote.requests.ChangePassword changePassword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1);
    
    /**
     * This function gets the current user's home screen statistics
     * @return an observable instance of [HomeStatistics]
     */
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "home")
    public abstract java.lang.Object getHomeStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.HomeStatistics> p0);
}