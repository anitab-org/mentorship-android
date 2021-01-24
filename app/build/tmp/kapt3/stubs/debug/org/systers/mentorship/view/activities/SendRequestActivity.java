package org.systers.mentorship.view.activities;

import java.lang.System;

/**
 * * This activity will show a Mentorship request detail from the Requests List
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006$"}, d2 = {"Lorg/systers/mentorship/view/activities/SendRequestActivity;", "Lorg/systers/mentorship/view/activities/BaseActivity;", "()V", "myCalendar", "Ljava/util/Calendar;", "pendingSentRelationships", "", "Lorg/systers/mentorship/models/Relationship;", "requestsViewModel", "Lorg/systers/mentorship/viewmodels/RequestsViewModel;", "sendRequestViewModel", "Lorg/systers/mentorship/viewmodels/SendRequestViewModel;", "getSendRequestViewModel", "()Lorg/systers/mentorship/viewmodels/SendRequestViewModel;", "sendRequestViewModel$delegate", "Lkotlin/Lazy;", "isRequestDuplicate", "", "newRelationship", "Lorg/systers/mentorship/remote/requests/RelationshipRequest;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "menuItem", "Landroid/view/MenuItem;", "populateView", "userName", "", "otherUserId", "", "currentUserId", "setObservables", "updateEndDateEditText", "Companion", "app_debug"})
public final class SendRequestActivity extends org.systers.mentorship.view.activities.BaseActivity {
    private java.util.Calendar myCalendar;
    private java.util.List<org.systers.mentorship.models.Relationship> pendingSentRelationships;
    private org.systers.mentorship.viewmodels.RequestsViewModel requestsViewModel;
    private final kotlin.Lazy sendRequestViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OTHER_USER_ID_INTENT_EXTRA = "OTHER_USER_ID_INTENT_EXTRA";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OTHER_USER_NAME_INTENT_EXTRA = "OTHER_USER_NAME_INTENT_EXTRA";
    public static final org.systers.mentorship.view.activities.SendRequestActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.SendRequestViewModel getSendRequestViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateEndDateEditText() {
    }
    
    private final void populateView(java.lang.String userName, int otherUserId, int currentUserId) {
    }
    
    private final void setObservables() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem menuItem) {
        return false;
    }
    
    private final boolean isRequestDuplicate(org.systers.mentorship.remote.requests.RelationshipRequest newRelationship) {
        return false;
    }
    
    public SendRequestActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/systers/mentorship/view/activities/SendRequestActivity$Companion;", "", "()V", "OTHER_USER_ID_INTENT_EXTRA", "", "OTHER_USER_NAME_INTENT_EXTRA", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}