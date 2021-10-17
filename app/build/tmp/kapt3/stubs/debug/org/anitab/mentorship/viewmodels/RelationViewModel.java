package org.anitab.mentorship.viewmodels;

import java.lang.System;

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\"\u0010\u0018\u001a\n \u0019*\u0004\u0018\u00010\n0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000e\u00a8\u0006!"}, d2 = {"Lorg/anitab/mentorship/viewmodels/RelationViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "mentorshipRelation", "Lorg/anitab/mentorship/models/Relationship;", "getMentorshipRelation", "()Lorg/anitab/mentorship/models/Relationship;", "setMentorshipRelation", "(Lorg/anitab/mentorship/models/Relationship;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "relationDataManager", "Lorg/anitab/mentorship/remote/datamanager/RelationDataManager;", "successfulCancel", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessfulCancel", "()Landroidx/lifecycle/MutableLiveData;", "successfulGet", "getSuccessfulGet", "tag", "kotlin.jvm.PlatformType", "getTag", "setTag", "cancelMentorshipRelation", "", "relationId", "", "getCurrentRelationDetails", "app_debug"})
public final class RelationViewModel extends androidx.lifecycle.ViewModel {
    private java.lang.String tag;
    private final org.anitab.mentorship.remote.datamanager.RelationDataManager relationDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successfulGet = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successfulCancel = null;
    public org.anitab.mentorship.models.Relationship mentorshipRelation;
    public java.lang.String message;
    
    public final java.lang.String getTag() {
        return null;
    }
    
    public final void setTag(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessfulGet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessfulCancel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.anitab.mentorship.models.Relationship getMentorshipRelation() {
        return null;
    }
    
    public final void setMentorshipRelation(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Relationship p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * Fetches current relation details
     */
    public final void getCurrentRelationDetails() {
    }
    
    /**
     * Cancels a mentorship relation
     */
    public final void cancelMentorshipRelation(int relationId) {
    }
    
    public RelationViewModel() {
        super();
    }
}