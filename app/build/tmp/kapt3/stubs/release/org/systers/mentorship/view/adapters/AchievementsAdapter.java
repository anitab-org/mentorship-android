package org.systers.mentorship.view.adapters;

import java.lang.System;

/**
 * * This class is the RecyclerView adapter for achievements. It is a subclass of [ListAdapter] for
 * * easy async calculation of diffs using DiffUtil to provide nice animations when the data set
 * * changes.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u000f"}, d2 = {"Lorg/systers/mentorship/view/adapters/AchievementsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/systers/mentorship/models/Task;", "Lorg/systers/mentorship/view/adapters/AchievementsAdapter$ViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"})
public final class AchievementsAdapter extends androidx.recyclerview.widget.ListAdapter<org.systers.mentorship.models.Task, org.systers.mentorship.view.adapters.AchievementsAdapter.ViewHolder> {
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.systers.mentorship.view.adapters.AchievementsAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.view.adapters.AchievementsAdapter.ViewHolder holder, int position) {
    }
    
    public AchievementsAdapter() {
        super(null);
    }
    
    /**
     * * This class holds a view for each item of the Achievements list
     *     * @param itemView represents each view of achievements list
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lorg/systers/mentorship/view/adapters/AchievementsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lorg/systers/mentorship/view/adapters/AchievementsAdapter;Landroid/view/View;)V", "bind", "", "task", "Lorg/systers/mentorship/models/Task;", "app_release"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        /**
         * * This function binds the description of the achievement to the textview
         *         * @param task The Achievement whose description is to be bound
         */
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.systers.mentorship.models.Task task) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}