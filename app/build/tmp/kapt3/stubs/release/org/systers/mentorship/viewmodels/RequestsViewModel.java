package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] used for Requests Screen
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020#H\u0007J\b\u0010%\u001a\u00020#H\u0007R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u001a\u0010\u001f\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000f\u00a8\u0006&"}, d2 = {"Lorg/systers/mentorship/viewmodels/RequestsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "allRequestsList", "", "Lorg/systers/mentorship/models/Relationship;", "getAllRequestsList", "()Ljava/util/List;", "setAllRequestsList", "(Ljava/util/List;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "pastRequestsList", "getPastRequestsList", "setPastRequestsList", "pendingAllRequestsList", "getPendingAllRequestsList", "setPendingAllRequestsList", "pendingSuccessful", "Landroidx/lifecycle/MutableLiveData;", "", "getPendingSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "relationDataManager", "Lorg/systers/mentorship/remote/datamanager/RelationDataManager;", "successful", "getSuccessful", "tag", "getTag", "setTag", "getAllMentorshipRelations", "", "getAllPendingMentorshipRelations", "getPastMentorshipRelations", "app_release"})
public final class RequestsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.RelationDataManager relationDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successful = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> pendingSuccessful = null;
    @org.jetbrains.annotations.NotNull()
    public java.util.List<org.systers.mentorship.models.Relationship> pendingAllRequestsList;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String message;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<org.systers.mentorship.models.Relationship> allRequestsList;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<org.systers.mentorship.models.Relationship> pastRequestsList;
    
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
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getPendingSuccessful() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.systers.mentorship.models.Relationship> getPendingAllRequestsList() {
        return null;
    }
    
    public final void setPendingAllRequestsList(@org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Relationship> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<org.systers.mentorship.models.Relationship> getAllRequestsList() {
        return null;
    }
    
    public final void setAllRequestsList(@org.jetbrains.annotations.Nullable()
    java.util.List<org.systers.mentorship.models.Relationship> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<org.systers.mentorship.models.Relationship> getPastRequestsList() {
        return null;
    }
    
    public final void setPastRequestsList(@org.jetbrains.annotations.Nullable()
    java.util.List<org.systers.mentorship.models.Relationship> p0) {
    }
    
    /**
     * * Fetches list of all Mentorship relations and requests
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getAllMentorshipRelations() {
    }
    
    /**
     * * Fetches list of all pending Mentorship relations and requests
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getAllPendingMentorshipRelations() {
    }
    
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getPastMentorshipRelations() {
    }
    
    public RequestsViewModel() {
        super();
    }
}