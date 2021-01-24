package org.systers.mentorship.view.activities;

import java.lang.System;

/**
 * * This activity will show a Mentorship request detail from the Requests List
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004H\u0002R#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lorg/systers/mentorship/view/activities/RequestDetailActivity;", "Lorg/systers/mentorship/view/activities/BaseActivity;", "()V", "mentorshipRelationResponse", "Lorg/systers/mentorship/models/Relationship;", "kotlin.jvm.PlatformType", "getMentorshipRelationResponse", "()Lorg/systers/mentorship/models/Relationship;", "mentorshipRelationResponse$delegate", "Lkotlin/Lazy;", "requestDetailViewModel", "Lorg/systers/mentorship/viewmodels/RequestDetailViewModel;", "getRequestDetailViewModel", "()Lorg/systers/mentorship/viewmodels/RequestDetailViewModel;", "requestDetailViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "populateView", "relationResponse", "setActionButtons", "setObservables", "setOnClickListeners", "setStateMessage", "app_debug"})
public final class RequestDetailActivity extends org.systers.mentorship.view.activities.BaseActivity {
    private final kotlin.Lazy requestDetailViewModel$delegate = null;
    private final kotlin.Lazy mentorshipRelationResponse$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.RequestDetailViewModel getRequestDetailViewModel() {
        return null;
    }
    
    private final org.systers.mentorship.models.Relationship getMentorshipRelationResponse() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void populateView(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    private final void setActionButtons(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    private final void setStateMessage(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    private final void setOnClickListeners(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    private final void setObservables(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem menuItem) {
        return false;
    }
    
    public RequestDetailActivity() {
        super();
    }
}