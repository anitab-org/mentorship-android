package org.anitab.mentorship.view.fragments;

import java.lang.System;

/**
 * This fragment is instantiated per each tab from the RequestsFragment ViewPager
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/anitab/mentorship/view/fragments/RequestPagerFragment;", "Lorg/anitab/mentorship/view/fragments/BaseFragment;", "()V", "emptyListText", "", "openRequestDetail", "Lkotlin/Function1;", "Lorg/anitab/mentorship/models/Relationship;", "", "requestsList", "", "getLayoutResourceId", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "setView", "Companion", "app_release"})
public final class RequestPagerFragment extends org.anitab.mentorship.view.fragments.BaseFragment {
    private java.util.List<org.anitab.mentorship.models.Relationship> requestsList;
    private java.lang.String emptyListText;
    private final kotlin.jvm.functions.Function1<org.anitab.mentorship.models.Relationship, kotlin.Unit> openRequestDetail = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.Integer> deletedRequests = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.view.fragments.RequestPagerFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected int getLayoutResourceId() {
        return 0;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void setView() {
    }
    
    public RequestPagerFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000f"}, d2 = {"Lorg/anitab/mentorship/view/fragments/RequestPagerFragment$Companion;", "", "()V", "deletedRequests", "", "", "getDeletedRequests", "()Ljava/util/Set;", "newInstance", "Lorg/anitab/mentorship/view/fragments/BaseFragment;", "requestsList", "", "Lorg/anitab/mentorship/models/Relationship;", "emptyListText", "", "app_release"})
    public static final class Companion {
        
        /**
         * Creates an instance of [RequestPagerFragment]
         */
        @org.jetbrains.annotations.NotNull()
        public final org.anitab.mentorship.view.fragments.BaseFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.util.List<org.anitab.mentorship.models.Relationship> requestsList, @org.jetbrains.annotations.NotNull()
        java.lang.String emptyListText) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> getDeletedRequests() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}