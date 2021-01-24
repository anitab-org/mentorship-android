package org.systers.mentorship.view.fragments;

import java.lang.System;

/**
 * * The fragment is responsible present the current mentorship relation  details
 */
@android.annotation.SuppressLint(value = {"ValidFragment"})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0003H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lorg/systers/mentorship/view/fragments/RelationFragment;", "Lorg/systers/mentorship/view/fragments/BaseFragment;", "mentorshipRelation", "Lorg/systers/mentorship/models/Relationship;", "(Lorg/systers/mentorship/models/Relationship;)V", "activityCast", "Lorg/systers/mentorship/view/activities/MainActivity;", "getActivityCast", "()Lorg/systers/mentorship/view/activities/MainActivity;", "activityCast$delegate", "Lkotlin/Lazy;", "alertDialog", "Landroidx/appcompat/app/AlertDialog$Builder;", "getAlertDialog", "()Landroidx/appcompat/app/AlertDialog$Builder;", "alertDialog$delegate", "relationViewModel", "Lorg/systers/mentorship/viewmodels/RelationViewModel;", "getRelationViewModel", "()Lorg/systers/mentorship/viewmodels/RelationViewModel;", "relationViewModel$delegate", "getLayoutResourceId", "", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "populateView", "relationResponse", "Companion", "app_debug"})
public final class RelationFragment extends org.systers.mentorship.view.fragments.BaseFragment {
    private final kotlin.Lazy relationViewModel$delegate = null;
    private final kotlin.Lazy activityCast$delegate = null;
    private final kotlin.Lazy alertDialog$delegate = null;
    private org.systers.mentorship.models.Relationship mentorshipRelation;
    private static final java.lang.String TAG = null;
    public static final org.systers.mentorship.view.fragments.RelationFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.RelationViewModel getRelationViewModel() {
        return null;
    }
    
    private final org.systers.mentorship.view.activities.MainActivity getActivityCast() {
        return null;
    }
    
    private final androidx.appcompat.app.AlertDialog.Builder getAlertDialog() {
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
    
    private final void populateView(org.systers.mentorship.models.Relationship relationResponse) {
    }
    
    public RelationFragment(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.Relationship mentorshipRelation) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\f"}, d2 = {"Lorg/systers/mentorship/view/fragments/RelationFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lorg/systers/mentorship/view/fragments/RelationFragment;", "mentorshipRelation", "Lorg/systers/mentorship/models/Relationship;", "app_debug"})
    public static final class Companion {
        
        /**
         * * Creates an instance of RelationFragment
         */
        @org.jetbrains.annotations.NotNull()
        public final org.systers.mentorship.view.fragments.RelationFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.systers.mentorship.models.Relationship mentorshipRelation) {
            return null;
        }
        
        public final java.lang.String getTAG() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}