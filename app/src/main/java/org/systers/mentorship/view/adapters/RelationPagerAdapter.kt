package org.systers.mentorship.view.adapters

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.fragments.RelationFragment
import org.systers.mentorship.view.fragments.TasksFragment

@SuppressLint("ValidFragment")
/**
 * This is the [FragmentStateAdapter] responsible for the configuration each fragment assigned to
 * each tabs. One tab displays the details of the current mentorship relation and the other provides
 * a detailed information about the tasks.
 */
class RelationPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val mentorshipRelation: Relationship
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    /**
     * This class represents the number and index of each tab of the layout
     */
    enum class TabsIndex(val value: Int) {
        DETAILS(0), TASKS(1)
    }

    val context = MentorshipApplication.getContext()

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        TabsIndex.DETAILS.value -> {
            RelationFragment.newInstance(mentorshipRelation)
        }

        TabsIndex.TASKS.value -> {
            TasksFragment.newInstance(mentorshipRelation)
        }
        else -> RelationFragment.newInstance(mentorshipRelation)
    }
}
