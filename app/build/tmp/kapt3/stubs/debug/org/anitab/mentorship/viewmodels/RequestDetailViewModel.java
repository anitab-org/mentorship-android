package org.anitab.mentorship.viewmodels;

import java.lang.System;

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00040\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\b\u00a8\u0006\u001a"}, d2 = {"Lorg/anitab/mentorship/viewmodels/RequestDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "relationDataManager", "Lorg/anitab/mentorship/remote/datamanager/RelationDataManager;", "successful", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "tag", "kotlin.jvm.PlatformType", "getTag", "setTag", "acceptRequest", "", "requestId", "", "deleteRequest", "rejectRequest", "app_debug"})
public final class RequestDetailViewModel extends androidx.lifecycle.ViewModel {
    private java.lang.String tag;
    private final org.anitab.mentorship.remote.datamanager.RelationDataManager relationDataManager = null;
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
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    public final void acceptRequest(int requestId) {
    }
    
    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    public final void rejectRequest(int requestId) {
    }
    
    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    public final void deleteRequest(int requestId) {
    }
    
    public RequestDetailViewModel() {
        super();
    }
}