package org.systers.mentorship.viewmodels

import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.remote.datamanager.UserDataManager


/**
 * This class represents the ViewModel for the HomeFragment
 */
class HomeViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName
    private val userDataManager by lazy { UserDataManager() }

    lateinit var homeStats: HomeStatistics

    fun getHomeStats() = observe(userDataManager.getHomeStats(), { homeStats = it })
}
