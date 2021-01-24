package org.systers.mentorship.view.adapters;

import java.lang.System;

/**
 * * This is the [FragmentPagerAdapter] responsible for the configuration each fragment assigned to
 * * each tabs. I will filter the [requestsList] and split it into 2 additional lists: pending
 * * and past requests lists
 * * @param requestsList list of all mentorship relations and requests
 * * @param fm fragment manager
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0019\u001a\u00020\u0016H\u0016R!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/systers/mentorship/view/adapters/RequestsPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "requestsList", "", "Lorg/systers/mentorship/models/Relationship;", "fm", "Landroidx/fragment/app/FragmentManager;", "pendingRequestsList", "(Ljava/util/List;Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "allList", "getAllList", "()Ljava/util/List;", "allList$delegate", "Lkotlin/Lazy;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "pastList", "getPastList", "pastList$delegate", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "TabsIndex", "app_release"})
public final class RequestsPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final kotlin.Lazy pastList$delegate = null;
    private final kotlin.Lazy allList$delegate = null;
    private final java.util.List<org.systers.mentorship.models.Relationship> requestsList = null;
    private final java.util.List<org.systers.mentorship.models.Relationship> pendingRequestsList = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    private final java.util.List<org.systers.mentorship.models.Relationship> getPastList() {
        return null;
    }
    
    private final java.util.List<org.systers.mentorship.models.Relationship> getAllList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.fragment.app.Fragment getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.CharSequence getPageTitle(int position) {
        return null;
    }
    
    public RequestsPagerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Relationship> requestsList, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm, @org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Relationship> pendingRequestsList) {
        super(null);
    }
    
    /**
     * * This class represents the number and index of each tab of the layout
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/systers/mentorship/view/adapters/RequestsPagerAdapter$TabsIndex;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "PENDING", "PAST", "ALL", "app_release"})
    public static enum TabsIndex {
        /*public static final*/ PENDING /* = new PENDING(0) */,
        /*public static final*/ PAST /* = new PAST(0) */,
        /*public static final*/ ALL /* = new ALL(0) */;
        private final int value = 0;
        
        public final int getValue() {
            return 0;
        }
        
        TabsIndex(int value) {
        }
    }
}