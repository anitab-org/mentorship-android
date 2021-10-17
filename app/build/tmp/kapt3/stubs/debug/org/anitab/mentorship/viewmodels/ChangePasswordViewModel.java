package org.anitab.mentorship.viewmodels;

import java.lang.System;

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/anitab/mentorship/viewmodels/ChangePasswordViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "successfulUpdate", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessfulUpdate", "()Landroidx/lifecycle/MutableLiveData;", "tag", "kotlin.jvm.PlatformType", "userDataManager", "Lorg/anitab/mentorship/remote/datamanager/UserDataManager;", "changeUserPassword", "", "changePassword", "Lorg/anitab/mentorship/remote/requests/ChangePassword;", "app_debug"})
public final class ChangePasswordViewModel extends androidx.lifecycle.ViewModel {
    private final java.lang.String tag = null;
    private final org.anitab.mentorship.remote.datamanager.UserDataManager userDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successfulUpdate = null;
    public java.lang.String message;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessfulUpdate() {
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
     * Updates the password of the current user
     */
    public final void changeUserPassword(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.remote.requests.ChangePassword changePassword) {
    }
    
    public ChangePasswordViewModel() {
        super();
    }
}