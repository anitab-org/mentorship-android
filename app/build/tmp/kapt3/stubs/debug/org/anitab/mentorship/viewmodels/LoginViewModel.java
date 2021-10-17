package org.anitab.mentorship.viewmodels;

import java.lang.System;

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\b\"\u0004\b\u0015\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/anitab/mentorship/viewmodels/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "authDataManager", "Lorg/anitab/mentorship/remote/datamanager/AuthDataManager;", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "preferenceManager", "Lorg/anitab/mentorship/utils/PreferenceManager;", "successful", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "tag", "kotlin.jvm.PlatformType", "getTag", "setTag", "login", "", "Lorg/anitab/mentorship/remote/requests/Login;", "app_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    private java.lang.String tag;
    private final org.anitab.mentorship.utils.PreferenceManager preferenceManager = null;
    private final org.anitab.mentorship.remote.datamanager.AuthDataManager authDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successful = null;
    public java.lang.String message;
    
    public final java.lang.String getTag() {
        return null;
    }
    
    public final void setTag(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessful() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * Will be used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    public final void login(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.Login login) {
    }
    
    public LoginViewModel() {
        super();
    }
}