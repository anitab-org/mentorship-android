package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
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
    }

    private val requestsViewModel by lazy {
        ViewModelProviders.of(this).get(RequestsViewModel::class.java)
    }
    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int = R.layout.fragment_requests

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        srlRequests.setOnRefreshListener { fetchNewest() }

        requestsViewModel.successful.observe(this, Observer {
            successful ->
            srlRequests.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    requestsViewModel.pendingSuccessful.observe(this, Observer {
                        successful ->
                        activityCast.hideProgressDialog()
                        if (successful != null) {
                            if (successful){
                                requestsViewModel.allRequestsList?.let { allRequestsList ->
                                    vpMentorshipRequests.adapter = RequestsPagerAdapter(allRequestsList, childFragmentManager, requestsViewModel.pendingAllRequestsList)
                                    tlMentorshipRequests.setupWithViewPager(vpMentorshipRequests)
                                }
                            }
                        }
                    })
                } else {
                    view?.let {
                        requestsViewModel.message?.let { message ->
                            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })

        fetchNewest()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
                fetchNewest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchNewest()  {
        srlRequests.isRefreshing = true
        requestsViewModel.getAllMentorshipRelations()
        requestsViewModel.getAllPendingMentorshipRelations()
        requestsViewModel.getPastMentorshipRelations()
    }
}
