package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName
    private val userDataManager by lazy { UserDataManager() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    val userStats = MutableLiveData<HomeStatistics>()

    init {
        userDataManager.getHomeStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    userStats.value = it as HomeStatistics
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

