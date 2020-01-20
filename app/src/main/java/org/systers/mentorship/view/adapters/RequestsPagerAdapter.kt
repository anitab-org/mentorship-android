package org.systers.mentorship.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.TOTAL_REQUEST_TABS
import org.systers.mentorship.utils.getUnixTimestampInMilliseconds
import org.systers.mentorship.view.fragments.RequestPagerFragment

/**
 * This is the [FragmentStateAdapter] responsible for the configuration each fragment assigned to
 * each tabs. It will filter the [requestsList] and split it into 2 additional lists: pending
 * and past requests lists
 * @param requestsList list of all mentorship relations and requests
 * @param fragmentManager fragment manager
 */
class RequestsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val requestsList: List<Relationship>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    /**
     * This class represents the number and index of each tab of the layout
     */
    enum class TabsIndex(val value: Int) {
        PENDING(0),
        PAST(1),
        ALL(2)
    }

    val context = MentorshipApplication.getContext()

    private val pendingList: List<Relationship> by lazy {
        requestsList.filter {
            val isPendingState = RelationState.PENDING.value == it.state
            val hasEndTimePassed = getUnixTimestampInMilliseconds(it.endsOn) < System.currentTimeMillis()

            isPendingState && !hasEndTimePassed
        }
    }
    private val pastList: List<Relationship> by lazy {
        requestsList.filter {
            val hasEndTimePassed = getUnixTimestampInMilliseconds(it.endsOn) < System.currentTimeMillis()
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

    override fun createFragment(position: Int): Fragment {
        when(position){
            TabsIndex.PENDING.value -> {
                return RequestPagerFragment.newInstance(
                        pendingList, context.getString(R.string.empty_pending_requests))
            }
            TabsIndex.PAST.value  -> {
                return RequestPagerFragment.newInstance(
                        pastList, context.getString(R.string.empty_past_requests))
            }
            TabsIndex.ALL.value  -> {
                return RequestPagerFragment.newInstance(
                        allList, context.getString(R.string.empty_requests))
            }
        }
        return RequestPagerFragment.newInstance(
                pendingList, context.getString(R.string.empty_pending_requests))
    }

    override fun getItemCount() = TOTAL_REQUEST_TABS
}
