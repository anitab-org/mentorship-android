package org.systers.mentorship.view.fragments;

import java.lang.System;

/**
 * * The fragment is responsible for showing the all mentorship requests
 * * and filtered lists such as for pending requests and past relations and requests
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001a"}, d2 = {"Lorg/systers/mentorship/view/fragments/RequestsFragment;", "Lorg/systers/mentorship/view/fragments/BaseFragment;", "()V", "activityCast", "Lorg/systers/mentorship/view/activities/MainActivity;", "getActivityCast", "()Lorg/systers/mentorship/view/activities/MainActivity;", "activityCast$delegate", "Lkotlin/Lazy;", "requestsViewModel", "Lorg/systers/mentorship/viewmodels/RequestsViewModel;", "getRequestsViewModel", "()Lorg/systers/mentorship/viewmodels/RequestsViewModel;", "requestsViewModel$delegate", "fetchNewest", "", "getLayoutResourceId", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "app_release"})
public final class RequestsFragment extends org.systers.mentorship.view.fragments.BaseFragment {
    private final kotlin.Lazy requestsViewModel$delegate = null;
    private final kotlin.Lazy activityCast$delegate = null;
    private static final java.lang.String TAG = null;
    public static final org.systers.mentorship.view.fragments.RequestsFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.RequestsViewModel getRequestsViewModel() {
        return null;
    }
    
    private final org.systers.mentorship.view.activities.MainActivity getActivityCast() {
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
    
    public RequestsFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lorg/systers/mentorship/view/fragments/RequestsFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lorg/systers/mentorship/view/fragments/RequestsFragment;", "app_release"})
    public static final class Companion {
        
        /**
         * * Creates an instance of [RequestsFragment]
         */
        @org.jetbrains.annotations.NotNull()
        public final org.systers.mentorship.view.fragments.RequestsFragment newInstance() {
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