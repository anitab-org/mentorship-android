package org.anitab.mentorship.view.adapters;

import java.lang.System;

/**
 * This class represents the DiffCallback for tasks, and is used in [AchievementsAdapter]
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lorg/anitab/mentorship/view/adapters/AchievementsItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/anitab/mentorship/models/Task;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
public final class AchievementsItemCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.anitab.mentorship.models.Task> {
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Task oldItem, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Task newItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Task oldItem, @org.jetbrains.annotations.NotNull()
    org.anitab.mentorship.models.Task newItem) {
        return false;
    }
    
    public AchievementsItemCallback() {
        super();
    }
}