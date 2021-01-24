package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] used for ProfileFragment
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\bR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/systers/mentorship/viewmodels/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "successfulGet", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessfulGet", "()Landroidx/lifecycle/MutableLiveData;", "successfulUpdate", "getSuccessfulUpdate", "tag", "getTag", "setTag", "user", "Lorg/systers/mentorship/models/User;", "getUser", "()Lorg/systers/mentorship/models/User;", "setUser", "(Lorg/systers/mentorship/models/User;)V", "userDataManager", "Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "getProfile", "", "updateProfile", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.UserDataManager userDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successfulGet = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successfulUpdate = null;
    @org.jetbrains.annotations.NotNull()
    public org.systers.mentorship.models.User user;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String message;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTag() {
        return null;
    }
    
    public final void setTag(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessfulGet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessfulUpdate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.systers.mentorship.models.User getUser() {
        return null;
    }
    
    public final void setUser(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.User p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * * Fetches the current users full profile
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getProfile() {
    }
    
    /**
     * * Updates the current user profile with data changed by the user
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void updateProfile(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.User user) {
    }
    
    public ProfileViewModel() {
        super();
    }
}