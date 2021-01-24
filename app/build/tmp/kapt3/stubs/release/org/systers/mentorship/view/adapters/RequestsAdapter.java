package org.systers.mentorship.view.adapters;

import java.lang.System;

/**
 * * This class represents the adapter that fills in each view of the Requests recyclerView
 * * @param requestsList list of request and relation to show
 * * @param openDetailFunction function to be called when an item from Requests list is clicked
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB6\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007\u00a2\u0006\u0002\u0010\fJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u000b2\b\b\u0001\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0012H\u0016R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R)\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/systers/mentorship/view/adapters/RequestsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/systers/mentorship/view/adapters/RequestsAdapter$RequestsViewHolder;", "requestsList", "", "Lorg/systers/mentorship/models/Relationship;", "openDetailFunction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "requestDetail", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "RequestsViewHolder", "app_release"})
public final class RequestsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.systers.mentorship.view.adapters.RequestsAdapter.RequestsViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final java.util.List<org.systers.mentorship.models.Relationship> requestsList = null;
    private final kotlin.jvm.functions.Function1<org.systers.mentorship.models.Relationship, kotlin.Unit> openDetailFunction = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.systers.mentorship.view.adapters.RequestsAdapter.RequestsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    org.systers.mentorship.view.adapters.RequestsAdapter.RequestsViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public RequestsAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<org.systers.mentorship.models.Relationship> requestsList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.systers.mentorship.models.Relationship, kotlin.Unit> openDetailFunction) {
        super();
    }
    
    /**
     * * This class holds a view for each item of the Requests list
     *     * @param itemView represents each view of Requests list
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/systers/mentorship/view/adapters/RequestsAdapter$RequestsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "app_release"})
    public static final class RequestsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        public RequestsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}