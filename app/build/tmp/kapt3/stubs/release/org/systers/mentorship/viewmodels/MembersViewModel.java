package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the [ViewModel] component used for the Members Activity
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0011H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u0006$"}, d2 = {"Lorg/systers/mentorship/viewmodels/MembersViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "currentPage", "", "getCurrentPage", "()I", "setCurrentPage", "(I)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "successful", "Landroidx/lifecycle/MutableLiveData;", "", "getSuccessful", "()Landroidx/lifecycle/MutableLiveData;", "tag", "getTag", "setTag", "userDataManager", "Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "userList", "Ljava/util/ArrayList;", "Lorg/systers/mentorship/models/User;", "Lkotlin/collections/ArrayList;", "getUserList", "()Ljava/util/ArrayList;", "setUserList", "(Ljava/util/ArrayList;)V", "getUsers", "", "isRefresh", "app_release"})
public final class MembersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tag;
    private final org.systers.mentorship.remote.datamanager.UserDataManager userDataManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successful = null;
    private int currentPage;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String message;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<org.systers.mentorship.models.User> userList;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTag() {
        return null;
    }
    
    public final void setTag(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessful() {
        return null;
    }
    
    public final int getCurrentPage() {
        return 0;
    }
    
    public final void setCurrentPage(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<org.systers.mentorship.models.User> getUserList() {
        return null;
    }
    
    public final void setUserList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<org.systers.mentorship.models.User> p0) {
    }
    
    /**
     * * Fetches users list from getUsers method of the UserService
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    public final void getUsers(boolean isRefresh) {
    }
    
    public MembersViewModel() {
        super();
    }
}