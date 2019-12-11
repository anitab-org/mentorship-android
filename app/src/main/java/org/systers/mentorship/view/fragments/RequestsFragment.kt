package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_requests.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
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
        var pendingRequestsList: List<Relationship> = mutableListOf()
    }

    private lateinit var requestsViewModel: RequestsViewModel

    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int = R.layout.fragment_requests

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requestsViewModel = ViewModelProviders.of(this).get(RequestsViewModel::class.java)

        /**
         * This processes the data from getPendingMentorshipRelations(), which returns all the pending requests,
         * It assigns the data to the pendingRequestsList , which is accessed in the RequestsPagerAdapter when calling for pending tab.
         * */
        requestsViewModel.successfulPending.observe(this, Observer {
            successful ->
            activityCast.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    pendingRequestsList=requestsViewModel.pendingRequestsList
                } else {
                    view?.let {
                        Snackbar.make(it, requestsViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
        activityCast.showProgressDialog(getString(R.string.fetching_requests))
        requestsViewModel.getPendingMentorshipRelations()


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
