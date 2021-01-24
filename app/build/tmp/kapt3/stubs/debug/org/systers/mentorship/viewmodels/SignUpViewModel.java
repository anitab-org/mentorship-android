package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] component used for the Sign Up Activity
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0013\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/systers/mentorship/viewmodels/SignUpViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "authDataManager", "Lorg/systers/mentorship/remote/datamanager/AuthDataManager;", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "successful", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "tag", "getTag", "setTag", "register", "", "Lorg/systers/mentorship/remote/requests/Register;", "app_debug"})
public final class SignUpViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.AuthDataManager authDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successful = null;
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
     * * Will be used to run the register method of the AuthService
     *     * @param register a registration request object containing the a user's registration fields
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void register(@org.jetbrains.annotations.NotNull()
    @io.reactivex.annotations.NonNull()
    org.systers.mentorship.remote.requests.Register register) {
    }
    
    public SignUpViewModel() {
        super();
    }
}