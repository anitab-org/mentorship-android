package org.systers.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_request_pager.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.activities.RequestDetailActivity
import org.systers.mentorship.view.activities.RequestDetailActivity.Companion.RELATION_INTENT_EXTRA
import org.systers.mentorship.view.adapters.RequestsAdapter

/**
 * This fragment is instantiated per each tab from the RequestsFragment ViewPager
 */
class RequestPagerFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [RequestPagerFragment]
         */
        fun newInstance(requestsList: List<Relationship>, emptyListText: String) : BaseFragment {

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

    private lateinit var requestsList: List<Relationship>
    private lateinit var emptyListText: String

    override fun getLayoutResourceId() = R.layout.fragment_request_pager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

    private val openRequestDetail: (Relationship) -> Unit =
            { requestDetail ->
                val intent = Intent(activity, RequestDetailActivity::class.java)
                intent.putExtra(RELATION_INTENT_EXTRA, requestDetail)
                startActivity(intent)
            }
}