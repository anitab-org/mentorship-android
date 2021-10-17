package org.anitab.mentorship.remote.datamanager;

import java.lang.System;

/**
 * This class represents the data manager related to Users API
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lorg/anitab/mentorship/remote/datamanager/UserDataManager;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "apiManager", "Lorg/anitab/mentorship/remote/ApiManager;", "getHomeStats", "Lorg/anitab/mentorship/models/HomeStatistics;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "Lorg/anitab/mentorship/models/User;", "userId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsers", "", "paginationRequest", "Lorg/anitab/mentorship/remote/requests/PaginationRequest;", "(Lorg/anitab/mentorship/remote/requests/PaginationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassword", "Lorg/anitab/mentorship/remote/responses/CustomResponse;", "changePassword", "Lorg/anitab/mentorship/remote/requests/ChangePassword;", "(Lorg/anitab/mentorship/remote/requests/ChangePassword;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "user", "(Lorg/anitab/mentorship/models/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserDataManager {
    private final org.anitab.mentorship.remote.ApiManager apiManager = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    /**
     * This will call the getVerifiedUsers method of UserService interface
     * @return an Observable of a list of [User]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.User>> p0) {
        return null;
    }
    
    /**
     * This will call the getVerifiedUsers(pagination) method of UserService interface
     * @return an Observable of a list of [User]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUsers(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.PaginationRequest paginationRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.anitab.mentorship.models.User>> p1) {
        return null;
    }
    
    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.User> p1) {
        return null;
    }
    
    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.User> p0) {
        return null;
    }
    
    /**
     * This will call the updateUser method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This will call the updatePassword method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePassword(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.ChangePassword changePassword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.remote.responses.CustomResponse> p1) {
        return null;
    }
    
    /**
     * This function fetches user statistics
     * @return an observable of [HomeStatistics]
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getHomeStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.anitab.mentorship.models.HomeStatistics> p0) {
        return null;
    }
    
    public UserDataManager(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    public UserDataManager() {
        super();
    }
}