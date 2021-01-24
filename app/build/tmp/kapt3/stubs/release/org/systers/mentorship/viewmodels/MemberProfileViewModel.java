package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001fH\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u00a8\u0006 "}, d2 = {"Lorg/systers/mentorship/viewmodels/MemberProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "successful", "Landroidx/lifecycle/MediatorLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MediatorLiveData;", "tag", "getTag", "setTag", "userDataManager", "Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "userId", "", "getUserId", "()I", "setUserId", "(I)V", "userProfile", "Lorg/systers/mentorship/models/User;", "getUserProfile", "()Lorg/systers/mentorship/models/User;", "setUserProfile", "(Lorg/systers/mentorship/models/User;)V", "", "app_release"})
public final class MemberProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.UserDataManager userDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> successful = null;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String message;
    @org.jetbrains.annotations.NotNull()
    public org.systers.mentorship.models.User userProfile;
    private int userId;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTag() {
        return null;
    }
    
    public final void setTag(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> getSuccessful() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.systers.mentorship.models.User getUserProfile() {
        return null;
    }
    
    public final void setUserProfile(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.User p0) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int p0) {
    }
    
    /**
     * * Fetches profile from a user with the value of [userId] by calling getUser method from UserService
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getUserProfile() {
    }
    
    public MemberProfileViewModel() {
        super();
    }
}