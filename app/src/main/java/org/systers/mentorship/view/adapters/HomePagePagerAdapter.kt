package org.systers.mentorship.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Task
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.fragments.HomePageAchievementFragment
import org.systers.mentorship.view.fragments.HomePageRelationFragment

/**
 * This is the [FragmentPagerAdapter] responsible for the configuration each fragment assigned to
 * each tabs.
 * @param achievements list of all achievements
 * @param pendingRequests number of pending requests
 * @param acceptedRequests number of accepted requests
 * @param rejectedRelations number of rejected requests
 * @param completedRelations number of completed relations
 * @param fm fragment manager
 */
class HomePagePagerAdapter(
        private val achievements: List<Task>,
        private val pendingRequests: Int,
        private val acceptedRequests: Int,
        private val completedRelations: Int,
        private val rejectedRelations: Int,
        fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    /**
     * This class represents the number and index of each tab of the layout
     */
    enum class TabsIndex(val value: Int) {
        RELATIONSHIPS(0),
        ACHIEVEMENTS(1)
    }

    val context = MentorshipApplication.getContext()

    override fun getItem(position: Int): Fragment {
        when(position){
            TabsIndex.RELATIONSHIPS.value -> {
                return HomePageRelationFragment.newInstance(pendingRequests, acceptedRequests, completedRelations, rejectedRelations)
            }
            TabsIndex.ACHIEVEMENTS.value  -> {
               return HomePageAchievementFragment.newInstance(
                        achievements)
            }
        }
        return HomePageRelationFragment.newInstance(pendingRequests, acceptedRequests, completedRelations, rejectedRelations)
    }

    override fun getCount(): Int = Constants.TOTAL_HOME_TABS

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            TabsIndex.RELATIONSHIPS.value -> {
                return context.getString(R.string.home_pager_relationships)
            }
            TabsIndex.ACHIEVEMENTS.value  -> {
                return context.getString(R.string.home_pager_achievements)
            }
        }
        return context.getString(R.string.home_pager_relationships)
    }
}
