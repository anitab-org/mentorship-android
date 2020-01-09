package org.systers.mentorship.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page_relation.*

import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants

/**
 * A simple [Fragment] subclass.
 */
class HomePageRelationFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [HomePageRelationFragment]
         */
        fun newInstance(pendingRequests: Int, acceptedRequests: Int, completedRelations: Int, rejectedRelations: Int) : BaseFragment {

            val args = Bundle()
            args.putInt(Constants.HOME_PAGE_RELATION_ACCEPTED, acceptedRequests)
            args.putInt(Constants.HOME_PAGE_RELATION_REJECTED, rejectedRelations)
            args.putInt(Constants.HOME_PAGE_RELATION_COMPLETED, completedRelations)
            args.putInt(Constants.HOME_PAGE_RELATION_PENDING, pendingRequests)

            val frag = HomePageRelationFragment()
            frag.arguments = args

            return frag
        }
    }

    private var pendingRequests: Int = 0
    private var acceptedRequests: Int = 0
    private var completedRelations: Int = 0
    private var rejectedRelations: Int = 0

    override fun getLayoutResourceId(): Int{
        return R.layout.fragment_home_page_relation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mcvAccepted.setOnClickListener {
            baseActivity.bottomNavigation.selectedItemId = R.id.navigation_relation
            replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(), R.string.fragment_title_relation)
        }

        mcvCompleted.setOnClickListener {
            baseActivity.bottomNavigation.selectedItemId = R.id.navigation_requests
            replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(), R.string.fragment_title_requests)
        }

        mcvRejected.setOnClickListener {
            baseActivity.bottomNavigation.selectedItemId = R.id.navigation_requests
            replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(), R.string.fragment_title_requests)
        }

        mcvPending.setOnClickListener {
            baseActivity.bottomNavigation.selectedItemId = R.id.navigation_requests
            replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(), R.string.fragment_title_requests)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            pendingRequests = it.getInt(Constants.HOME_PAGE_RELATION_PENDING)
            acceptedRequests = it.getInt(Constants.HOME_PAGE_RELATION_ACCEPTED)
            completedRelations = it.getInt(Constants.HOME_PAGE_RELATION_COMPLETED)
            rejectedRelations = it.getInt(Constants.HOME_PAGE_RELATION_REJECTED)
        }
        setView()
    }

    private fun setView() {
        if (pendingRequests != null && acceptedRequests != null && completedRelations != null
                && rejectedRelations != null)
        {
            tvHomePageRelationAccepted.text = acceptedRequests.toString()
            tvHomePageRelationCompleted.text = completedRelations.toString()
            tvHomePageRelationPending.text = pendingRequests.toString()
            tvHomePageRelationRejected.text = rejectedRelations.toString()
        }
    }

    fun replaceFragment(containerId: Int, fragment: Fragment, title: Int) {
        activity?.supportFragmentManager?.beginTransaction()
                ?.replace(containerId, fragment, getString(title))
                ?.commit()
        baseActivity.supportActionBar?.setTitle(title)
    }
}
