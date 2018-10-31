package org.systers.mentorship.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.fragment_requests.*
import org.systers.mentorship.R
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.RequestsPagerAdapter
import org.systers.mentorship.viewmodels.RequestsViewModel

/**
 * The fragment is responsible for showing the all mentorship requests
 * and filtered lists such as for pending requests and past relations and requests
 */
class RequestsFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [RequestsFragment]
         */
        fun newInstance() = RequestsFragment()

        val TAG = RelationFragment::class.java.simpleName
        const val NUMBER_OF_PAGES = 3
    }

    private lateinit var requestsViewModel: RequestsViewModel

    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int = R.layout.fragment_requests

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requestsViewModel = ViewModelProviders.of(this).get(RequestsViewModel::class.java)
        requestsViewModel.successful.observe(this, Observer {
            successful ->
            activityCast.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                        vpMentorshipRequests.adapter = RequestsPagerAdapter(requestsViewModel.allRequestsList, childFragmentManager)
                        tlMentorshipRequests.setupWithViewPager(vpMentorshipRequests)
                } else {
                    view?.let {
                        Snackbar.make(it, requestsViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        activityCast.showProgressDialog(getString(R.string.fetching_requests))
        requestsViewModel.getAllMentorshipRelations()
    }
}
