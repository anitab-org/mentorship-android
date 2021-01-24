package org.systers.mentorship.viewmodels;

import java.lang.System;

/**
 * * This class represents the ViewModel for the HomeFragment
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001cH\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lorg/systers/mentorship/viewmodels/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_message", "Lorg/systers/mentorship/utils/SingleLiveEvent;", "", "_userStats", "Landroidx/lifecycle/MutableLiveData;", "Lorg/systers/mentorship/models/HomeStatistics;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "message", "Landroidx/lifecycle/LiveData;", "getMessage", "()Landroidx/lifecycle/LiveData;", "tag", "userDataManager", "Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "getUserDataManager", "()Lorg/systers/mentorship/remote/datamanager/UserDataManager;", "userDataManager$delegate", "userStats", "getUserStats", "getHomeStats", "", "onCleared", "app_release"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    private final java.lang.String tag = null;
    private final kotlin.Lazy userDataManager$delegate = null;
    private final kotlin.Lazy compositeDisposable$delegate = null;
    private final androidx.lifecycle.MutableLiveData<org.systers.mentorship.models.HomeStatistics> _userStats = null;
    private final org.systers.mentorship.utils.SingleLiveEvent<java.lang.String> _message = null;
    
    private final org.systers.mentorship.remote.datamanager.UserDataManager getUserDataManager() {
        return null;
    }
    
    private final io.reactivex.disposables.CompositeDisposable getCompositeDisposable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.systers.mentorship.models.HomeStatistics> getUserStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMessage() {
        return null;
    }
    
    /**
     * * Fetches home stats from getHomeStats method of the UserService
     */
    public final void getHomeStats() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public HomeViewModel() {
        super();
    }
}