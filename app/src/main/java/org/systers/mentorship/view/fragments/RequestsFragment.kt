package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_requests.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants.isNetworkAvailable
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

    private val requestsViewModel: RequestsViewModel by viewModels()
    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int = R.layout.fragment_requests

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        srlRequests.setOnRefreshListener { fetchNewest() }

        requestsViewModel.successful.observe(
            viewLifecycleOwner,
            { successful ->
                srlRequests.isRefreshing = false
                if (successful != null) {
                    if (successful) {
                        requestsViewModel.pendingSuccessful.observe(
                            viewLifecycleOwner,
                            { successful ->
                                activityCast.hideProgressDialog()
                                if (successful != null) {
                                    if (successful) {
                                        vpMentorshipRequests.adapter = RequestsPagerAdapter(
                                            requestsViewModel.allRequestsList,
                                            requestsViewModel.pendingAllRequestsList,
                                            requireActivity()
                                        )
                                        TabLayoutMediator(
                                            tlMentorshipRequests,
                                            vpMentorshipRequests
                                        ) { tab, position ->
                                            when (position) {
                                                0 -> {
                                                    tab.text = context?.getString(R.string.pending)
                                                }
                                                1 -> {
                                                    tab.text = context?.getString(R.string.past)
                                                }
                                                2 -> {
                                                    tab.text = context?.getString(R.string.all)
                                                }
                                            }
                                        }.attach()
                                    }
                                } else {
                                    view?.let {
                                        requestsViewModel.message?.let { message ->
                                            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        )

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

    private fun fetchNewest() {
        srlRequests.isRefreshing = true
        if (!isNetworkAvailable(requireContext())) {
            requestsViewModel.getAllRelationshipsFromDb(requireContext())
                .observe(
                    viewLifecycleOwner,
                    {
                        if (it.isNullOrEmpty()) requestsViewModel.successful.postValue(false)
                        else {
                            requestsViewModel.allRequestsList =
                                it.sortedByDescending { it.creationDate }
                            requestsViewModel.successful.postValue(true)
                        }
                    }
                )
            requestsViewModel.getAllPendingRelationshipsFromDb(requireContext())
                .observe(
                    viewLifecycleOwner,
                    {
                        if (it.isNullOrEmpty()) requestsViewModel.pendingSuccessful.postValue(true)
                        else {
                            requestsViewModel.pendingAllRequestsList =
                                it.sortedByDescending { it.creationDate }
                            requestsViewModel.pendingSuccessful.postValue(true)
                        }
                    }
                )
        } else {
            requestsViewModel.getAllMentorshipRelations(requireContext())
            requestsViewModel.getAllPendingMentorshipRelations(requireContext())
            requestsViewModel.getPastMentorshipRelations(requireContext())
        }
    }
}
