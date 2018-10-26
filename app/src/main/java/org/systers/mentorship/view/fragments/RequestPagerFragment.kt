package org.systers.mentorship.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_request_pager.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.MentorshipRelationState
import org.systers.mentorship.remote.responses.MentorshipRelationResponse
import org.systers.mentorship.utils.getUnixTimestampInMilliseconds
import org.systers.mentorship.view.activities.RequestDetailActivity
import org.systers.mentorship.view.activities.RequestDetailActivity.Companion.RELATION_INTENT_EXTRA
import org.systers.mentorship.view.adapters.RequestsAdapter
import org.systers.mentorship.view.adapters.RequestsPagerAdapter
import org.systers.mentorship.viewmodels.RequestsViewModel

/**
 * This fragment is instantiated per each tab from the RequestsFragment ViewPager
 */
class RequestPagerFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [RequestPagerFragment]
         */
        fun newInstance(requestsList: List<MentorshipRelationResponse>, emptyListText: String): BaseFragment {

            val args = Bundle()
            args.putParcelableArrayList(REQUESTS_LIST_BUNDLE_ARG, ArrayList(requestsList))
            args.putString(EMPTY_LIST_TEXT_BUNDLE_ARG, emptyListText)

            val frag = RequestPagerFragment()
            frag.arguments = args

            return frag
        }

        private const val REQUESTS_LIST_BUNDLE_ARG = "REQUESTS_LIST_BUNDLE_ARG"
        private const val EMPTY_LIST_TEXT_BUNDLE_ARG = "EMPTY_LIST_TEXT_BUNDLE_ARG"

        val TAG = RequestPagerFragment::class.java.simpleName
    }

    private lateinit var requestsList: List<MentorshipRelationResponse>
    private lateinit var emptyListText: String

    override fun getLayoutResourceId() = R.layout.fragment_request_pager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("RequestPagerFragment", "onActivityCreated Called")

        arguments?.let {
            requestsList = it.getParcelableArrayList(REQUESTS_LIST_BUNDLE_ARG)
            emptyListText = it.getString(EMPTY_LIST_TEXT_BUNDLE_ARG)
        }

        if (requestsList.isEmpty()) {
            tvEmptyList.text = emptyListText
            rvRequestsList.visibility = View.GONE
        } else {
            rvRequestsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RequestsAdapter(requestsList, openRequestDetail)
            }
            tvEmptyList.visibility = View.GONE
        }
    }

    private val openRequestDetail: (MentorshipRelationResponse) -> Unit =
            { requestDetail ->
                val intent = Intent(activity, RequestDetailActivity::class.java)
                intent.putExtra(RELATION_INTENT_EXTRA, requestDetail)
                startActivity(intent)
            }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("RequestPagerFragment", "onAttach Called")
    }

    override fun onResume() {
        super.onResume()
        Log.e("RequestPagerFragment", "onResume Called")



        /**  var pendingList : List<MentorshipRelationResponse> = requestsList.filter {
        val isPendingState = MentorshipRelationState.PENDING.value == it.state
        val hasEndTimePassed = getUnixTimestampInMilliseconds(it.endAtTimestamp) < System.currentTimeMillis()
        isPendingState && !hasEndTimePassed
        }

        newInstance(pendingList, context!!.getString(R.string.empty_pending_requests))

        arguments?.let {
        requestsList = it.getParcelableArrayList(REQUESTS_LIST_BUNDLE_ARG)
        emptyListText = it.getString(EMPTY_LIST_TEXT_BUNDLE_ARG)
        }

        if (requestsList.isEmpty()) {
        tvEmptyList.text = emptyListText
        rvRequestsList.visibility = View.GONE
        } else {
        rvRequestsList.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = RequestsAdapter(requestsList, openRequestDetail)
        }
        tvEmptyList.visibility = View.GONE
        }
        } */


    }
}