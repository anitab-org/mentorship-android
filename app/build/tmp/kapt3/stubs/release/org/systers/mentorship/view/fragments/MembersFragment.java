package org.systers.mentorship.view.fragments;

import java.lang.System;

/**
 * * The fragment is responsible for showing all the members of the system in a list format
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 42\u00020\u0001:\u000245B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0014J\u0012\u0010\u001c\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\"\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+H\u0016J \u0010\u0010\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lorg/systers/mentorship/view/fragments/MembersFragment;", "Lorg/systers/mentorship/view/fragments/BaseFragment;", "()V", "filterMap", "Ljava/util/HashMap;", "", "isLoading", "", "isRecyclerView", "memberListInitialized", "membersViewModel", "Lorg/systers/mentorship/viewmodels/MembersViewModel;", "getMembersViewModel", "()Lorg/systers/mentorship/viewmodels/MembersViewModel;", "membersViewModel$delegate", "Lkotlin/Lazy;", "openUserProfile", "Lkotlin/Function1;", "", "", "rvAdapter", "Lorg/systers/mentorship/view/adapters/MembersAdapter;", "addLoadMoreListener", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "fetchNewest", "isRefresh", "getLayoutResourceId", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "memberId", "sharedImageView", "Landroid/widget/ImageView;", "sharedTextView", "Landroid/widget/TextView;", "runLayoutAnimation", "searchUsers", "query", "Companion", "SortValues", "app_release"})
public final class MembersFragment extends org.systers.mentorship.view.fragments.BaseFragment {
    private boolean memberListInitialized;
    private final kotlin.Lazy membersViewModel$delegate = null;
    private org.systers.mentorship.view.adapters.MembersAdapter rvAdapter;
    private boolean isLoading;
    private boolean isRecyclerView;
    private java.util.HashMap<java.lang.String, java.lang.String> filterMap;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> openUserProfile = null;
    public static final org.systers.mentorship.view.fragments.MembersFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.MembersViewModel getMembersViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected int getLayoutResourceId() {
        return 0;
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    public final void searchUsers(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void runLayoutAnimation(androidx.recyclerview.widget.RecyclerView recyclerView) {
    }
    
    private final void addLoadMoreListener(androidx.recyclerview.widget.RecyclerView recyclerView) {
    }
    
    private final void openUserProfile(int memberId, android.widget.ImageView sharedImageView, android.widget.TextView sharedTextView) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void fetchNewest(boolean isRefresh) {
    }
    
    public MembersFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/systers/mentorship/view/fragments/MembersFragment$SortValues;", "", "(Ljava/lang/String;I)V", "NAMEAZ", "NAMEZA", "REGISTRATION_DATE", "app_release"})
    public static enum SortValues {
        /*public static final*/ NAMEAZ /* = new NAMEAZ() */,
        /*public static final*/ NAMEZA /* = new NAMEZA() */,
        /*public static final*/ REGISTRATION_DATE /* = new REGISTRATION_DATE() */;
        
        SortValues() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/systers/mentorship/view/fragments/MembersFragment$Companion;", "", "()V", "newInstance", "Lorg/systers/mentorship/view/fragments/MembersFragment;", "app_release"})
    public static final class Companion {
        
        /**
         * * Creates an instance of [MembersFragment]
         */
        @org.jetbrains.annotations.NotNull()
        public final org.systers.mentorship.view.fragments.MembersFragment newInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}