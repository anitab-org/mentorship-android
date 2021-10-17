package org.anitab.mentorship.view.adapters;

import java.lang.System;

/**
 * This class represents the adapter that fills in each view of the Members recyclerView
 * @param userList list of users to show
 * @param openDetailFunction function to be called when an item from Members list is clicked
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00017Bl\u0012\u0018\b\u0002\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012K\u0010\u0007\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\b\u00a2\u0006\u0002\u0010\u0012J@\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"2\"\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0019`$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0002J\b\u0010&\u001a\u00020\tH\u0016J!\u0010\'\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010)H\u0002\u00a2\u0006\u0002\u0010+J\u001a\u0010,\u001a\u00020\u00112\b\b\u0001\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\tH\u0016J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\tH\u0016J\u0010\u00103\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0002H\u0016J\u0016\u00104\u001a\u00020\u00112\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0002J8\u00106\u001a\u00020\u00112\"\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0019`$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\"R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 RS\u0010\u0007\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lorg/anitab/mentorship/view/adapters/MembersAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/anitab/mentorship/view/adapters/MembersAdapter$MembersViewHolder;", "userList", "Ljava/util/ArrayList;", "Lorg/anitab/mentorship/models/User;", "Lkotlin/collections/ArrayList;", "openDetailFunction", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "memberId", "Landroid/widget/ImageView;", "sharedImageView", "Landroid/widget/TextView;", "sharedTextView", "", "(Ljava/util/ArrayList;Lkotlin/jvm/functions/Function3;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "filterMap", "Ljava/util/HashMap;", "", "filteredUserList", "", "lastPosition", "getLastPosition", "()I", "setLastPosition", "(I)V", "getFilteredUsers", "", "map", "Lkotlin/collections/HashMap;", "newUsers", "getItemCount", "getMentorshipAvailabilityText", "availableToMentor", "", "needMentoring", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Ljava/lang/String;", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewDetachedFromWindow", "setData", "users", "updateUsersList", "MembersViewHolder", "app_release"})
public final class MembersAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.anitab.mentorship.view.adapters.MembersAdapter.MembersViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private int lastPosition = -1;
    private java.util.HashMap<java.lang.String, java.lang.String> filterMap;
    private java.util.List<org.anitab.mentorship.models.User> filteredUserList;
    private java.util.ArrayList<org.anitab.mentorship.models.User> userList;
    private final kotlin.jvm.functions.Function3<java.lang.Integer, android.widget.ImageView, android.widget.TextView, kotlin.Unit> openDetailFunction = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final int getLastPosition() {
        return 0;
    }
    
    public final void setLastPosition(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.anitab.mentorship.view.adapters.MembersAdapter.MembersViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    org.anitab.mentorship.view.adapters.MembersAdapter.MembersViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateUsersList(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> map, @org.jetbrains.annotations.NotNull()
    java.util.List<org.anitab.mentorship.models.User> newUsers) {
    }
    
    private final java.util.List<org.anitab.mentorship.models.User> getFilteredUsers(java.util.HashMap<java.lang.String, java.lang.String> map, java.util.List<org.anitab.mentorship.models.User> newUsers) {
        return null;
    }
    
    private final void setData(java.util.List<org.anitab.mentorship.models.User> users) {
    }
    
    private final java.lang.String getMentorshipAvailabilityText(java.lang.Boolean availableToMentor, java.lang.Boolean needMentoring) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewDetachedFromWindow(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.view.adapters.MembersAdapter.MembersViewHolder holder) {
    }
    
    public MembersAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<org.anitab.mentorship.models.User> userList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super android.widget.ImageView, ? super android.widget.TextView, kotlin.Unit> openDetailFunction) {
        super();
    }
    
    /**
     * This class holds a view for each item of the Members list
     * @param itemView represents each view of Members list
     */
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/anitab/mentorship/view/adapters/MembersAdapter$MembersViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "app_release"})
    public static final class MembersViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        public MembersViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}