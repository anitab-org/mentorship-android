package org.anitab.mentorship.view.activities;

import java.lang.System;

/**
 * This activity will show the public profile of a user of the system
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0014J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/anitab/mentorship/view/activities/MemberProfileActivity;", "Lorg/anitab/mentorship/view/activities/BaseActivity;", "()V", "currentUser", "Lorg/anitab/mentorship/models/User;", "memberProfileViewModel", "Lorg/anitab/mentorship/viewmodels/MemberProfileViewModel;", "getMemberProfileViewModel", "()Lorg/anitab/mentorship/viewmodels/MemberProfileViewModel;", "memberProfileViewModel$delegate", "Lkotlin/Lazy;", "userProfile", "fetchNewest", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "menuItem", "Landroid/view/MenuItem;", "setCurrentUser", "user", "setUserProfile", "app_release"})
public final class MemberProfileActivity extends org.anitab.mentorship.view.activities.BaseActivity {
    private final kotlin.Lazy memberProfileViewModel$delegate = null;
    private org.anitab.mentorship.models.User userProfile;
    private org.anitab.mentorship.models.User currentUser;
    private java.util.HashMap _$_findViewCache;
    
    private final org.anitab.mentorship.viewmodels.MemberProfileViewModel getMemberProfileViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem menuItem) {
        return false;
    }
    
    private final void fetchNewest() {
    }
    
    private final void setCurrentUser(org.anitab.mentorship.models.User user) {
    }
    
    private final void setUserProfile(org.anitab.mentorship.models.User user) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public MemberProfileActivity() {
        super();
    }
}