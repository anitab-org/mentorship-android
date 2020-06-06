package org.systers.mentorship.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.getUnixTimestampInMilliseconds
import org.systers.mentorship.view.fragments.RequestPagerFragment

/**
 * This is the [FragmentStateAdapter] responsible for the configuration each fragment assigned to
 * each tabs. I will filter the [requestsList] and split it into 2 additional lists: pending
 * and past requests lists
 * @param requestsList list of all mentorship relations and requests
 * @param fm fragment manager
 */

class NewRequestPagerAdapter(private val requestsList: List<Relationship>,
                             fm: Fragment,
                             private val pendingRequestsList: List<Relationship>) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return Constants.TOTAL_REQUEST_TABS
    }

    val context = MentorshipApplication.getContext()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TabsIndex.PENDING.value -> {
                RequestPagerFragment.newInstance(
                        pendingRequestsList, context.getString(R.string.empty_pending_requests))
            }
            TabsIndex.PAST.value -> {
                RequestPagerFragment.newInstance(
                        pastList, context.getString(R.string.empty_past_requests))
            }
            else -> {
                RequestPagerFragment.newInstance(
                        allList, context.getString(R.string.empty_requests))
            }
        }
    }

    private val pastList: List<Relationship> by lazy {
        requestsList.filter {
            val hasEndTimePassed = getUnixTimestampInMilliseconds(it.endDate) < System.currentTimeMillis()
            val isAcceptedState = RelationState.ACCEPTED.value == it.state

            !isAcceptedState && hasEndTimePassed
        }
    }
    private val allList: List<Relationship> by lazy {
        requestsList.filter {
            val isAcceptedState = RelationState.ACCEPTED.value == it.state

            !isAcceptedState
        }
    }

    /**
     * This class represents the number and index of each tab of the layout
     */
    enum class TabsIndex(val value: Int) {
        PENDING(0),
        PAST(1),
        ALL(2)
    }
}