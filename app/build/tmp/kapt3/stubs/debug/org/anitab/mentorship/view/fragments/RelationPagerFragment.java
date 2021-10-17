package org.anitab.mentorship.view.fragments;

import java.lang.System;

/**
 * This fragment is instantiated per each tab from the RelationFragment ViewPager
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lorg/anitab/mentorship/view/fragments/RelationPagerFragment;", "Lorg/anitab/mentorship/view/fragments/BaseFragment;", "()V", "activityCast", "Lorg/anitab/mentorship/view/activities/MainActivity;", "getActivityCast", "()Lorg/anitab/mentorship/view/activities/MainActivity;", "activityCast$delegate", "Lkotlin/Lazy;", "relationViewModel", "Lorg/anitab/mentorship/viewmodels/RelationViewModel;", "getRelationViewModel", "()Lorg/anitab/mentorship/viewmodels/RelationViewModel;", "relationViewModel$delegate", "fetchNewest", "", "getLayoutResourceId", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "updateView", "mentorshipRelation", "Lorg/anitab/mentorship/models/Relationship;", "Companion", "app_debug"})
public final class RelationPagerFragment extends org.anitab.mentorship.view.fragments.BaseFragment {
    private final kotlin.Lazy relationViewModel$delegate = null;
    private final kotlin.Lazy activityCast$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.view.fragments.RelationPagerFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.anitab.mentorship.viewmodels.RelationViewModel getRelationViewModel() {
        return null;
    }
    
    private final org.anitab.mentorship.view.activities.MainActivity getActivityCast() {
        return null;
    }
    
    @java.lang.Override()
    protected int getLayoutResourceId() {
        return 0;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void fetchNewest() {
    }
    
    private final void updateView(org.anitab.mentorship.models.Relationship mentorshipRelation) {
    }
    
    public RelationPagerFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/anitab/mentorship/view/fragments/RelationPagerFragment$Companion;", "", "()V", "newInstance", "Lorg/anitab/mentorship/view/fragments/RelationPagerFragment;", "app_debug"})
    public static final class Companion {
        
        /**
         * Creates an instance of [RelationPagerFragment]
         */
        @org.jetbrains.annotations.NotNull()
        public final org.anitab.mentorship.view.fragments.RelationPagerFragment newInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}