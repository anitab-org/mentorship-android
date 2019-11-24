package org.systers.mentorship.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.getUnixTimestampInMilliseconds
import org.systers.mentorship.view.fragments.RequestPagerFragment

/**
 * This is the [FragmentPagerAdapter] responsible for the configuration each fragment assigned to
 * each tabs. I will filter the [requestsList] and split it into 2 additional lists: pending
 * and past requests lists
 * @param requestsList list of all mentorship relations and requests
 * @param fm fragment manager
 */
class RequestsPagerAdapter(
        private val requestsList: List<Relationship>,
        fm: FragmentManager
) : FragmentPagerAdapter(fm) {

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

    override fun getItem(position: Int): Fragment {
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

    override fun getCount(): Int = Constants.TOTAL_REQUEST_TABS

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            TabsIndex.PENDING.value -> {
                return context.getString(R.string.pending)
            }
            TabsIndex.PAST.value  -> {
                return context.getString(R.string.past)
            }
            TabsIndex.ALL.value  -> {
                return context.getString(R.string.all)
            }
        }
        return context.getString(R.string.pending)
    }
}
