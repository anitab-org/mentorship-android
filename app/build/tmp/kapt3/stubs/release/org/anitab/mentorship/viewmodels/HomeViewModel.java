package org.anitab.mentorship.viewmodels;

import java.lang.System;

/**
 * This class represents the ViewModel for the HomeFragment
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/anitab/mentorship/viewmodels/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_message", "Lorg/anitab/mentorship/utils/SingleLiveEvent;", "", "_userStats", "Landroidx/lifecycle/MutableLiveData;", "Lorg/anitab/mentorship/models/HomeStatistics;", "message", "Landroidx/lifecycle/LiveData;", "getMessage", "()Landroidx/lifecycle/LiveData;", "tag", "kotlin.jvm.PlatformType", "userDataManager", "Lorg/anitab/mentorship/remote/datamanager/UserDataManager;", "getUserDataManager", "()Lorg/anitab/mentorship/remote/datamanager/UserDataManager;", "userDataManager$delegate", "Lkotlin/Lazy;", "userStats", "getUserStats", "getHomeStats", "", "app_release"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    private final java.lang.String tag = null;
    private final kotlin.Lazy userDataManager$delegate = null;
    private final androidx.lifecycle.MutableLiveData<org.anitab.mentorship.models.HomeStatistics> _userStats = null;
    private final org.anitab.mentorship.utils.SingleLiveEvent<java.lang.String> _message = null;
    
    private final org.anitab.mentorship.remote.datamanager.UserDataManager getUserDataManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.anitab.mentorship.models.HomeStatistics> getUserStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMessage() {
        return null;
    }
    
    /**
     * Fetches home stats from getHomeStats method of the UserService
     */
    public final void getHomeStats() {
    }
    
    public HomeViewModel() {
        super();
    }
}