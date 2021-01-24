package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] component used for the Send Request Activity
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u0016H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\b\u00a8\u0006\u0017"}, d2 = {"Lorg/systers/mentorship/viewmodels/SendRequestViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "relationDataManager", "Lorg/systers/mentorship/remote/datamanager/RelationDataManager;", "successful", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "tag", "getTag", "setTag", "sendRequest", "", "relationshipRequest", "Lorg/systers/mentorship/remote/requests/RelationshipRequest;", "app_debug"})
public final class SendRequestViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.RelationDataManager relationDataManager = null;
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
     * * Call send a mentorship request service
     *     * @param relationshipRequest object containing mentorship request details
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void sendRequest(@org.jetbrains.annotations.NotNull()
    @io.reactivex.annotations.NonNull()
    org.systers.mentorship.remote.requests.RelationshipRequest relationshipRequest) {
    }
    
    public SendRequestViewModel() {
        super();
    }
}