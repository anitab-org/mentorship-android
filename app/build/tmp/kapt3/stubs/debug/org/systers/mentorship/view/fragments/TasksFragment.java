package org.systers.mentorship.view.fragments;

import java.lang.System;

/**
 * * The fragment is responsible for showing the all mentorship tasks
 * * and achievements. It also allows to add new tasks and mark them as complete.
 */
@android.annotation.SuppressLint(value = {"ValidFragment"})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/systers/mentorship/view/fragments/TasksFragment;", "Lorg/systers/mentorship/view/fragments/BaseFragment;", "mentorshipRelation", "Lorg/systers/mentorship/models/Relationship;", "(Lorg/systers/mentorship/models/Relationship;)V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "taskViewModel", "Lorg/systers/mentorship/viewmodels/TasksViewModel;", "getTaskViewModel", "()Lorg/systers/mentorship/viewmodels/TasksViewModel;", "taskViewModel$delegate", "Lkotlin/Lazy;", "getLayoutResourceId", "", "markTask", "", "taskId", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "showCompleteDialog", "Companion", "app_debug"})
public final class TasksFragment extends org.systers.mentorship.view.fragments.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    private final kotlin.Lazy taskViewModel$delegate = null;
    private org.systers.mentorship.models.Relationship mentorshipRelation;
    private static final java.lang.String TAG = null;
    public static final org.systers.mentorship.view.fragments.TasksFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    private final org.systers.mentorship.viewmodels.TasksViewModel getTaskViewModel() {
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
    
    /**
     * * The function creates a dialog box through which new tasks can be added
     */
    private final void showCompleteDialog() {
    }
    
    private final void markTask(int taskId) {
    }
    
    public TasksFragment(@org.jetbrains.annotations.NotNull()
    org.systers.mentorship.models.Relationship mentorshipRelation) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\f"}, d2 = {"Lorg/systers/mentorship/view/fragments/TasksFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lorg/systers/mentorship/view/fragments/TasksFragment;", "mentorshipRelation", "Lorg/systers/mentorship/models/Relationship;", "app_debug"})
    public static final class Companion {
        
        /**
         * * Creates an instance of [TasksFragment]
         */
        @org.jetbrains.annotations.NotNull()
        public final org.systers.mentorship.view.fragments.TasksFragment newInstance(@org.jetbrains.annotations.NotNull()
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