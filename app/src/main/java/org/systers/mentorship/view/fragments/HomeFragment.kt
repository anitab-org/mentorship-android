package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentHomeBinding
import org.systers.mentorship.view.adapters.AchievementsAdapter
import org.systers.mentorship.viewmodels.HomeViewModel

/**
 * The fragment is responsible for showing a welcoming message and show some statistics of the User
 * usage of the app
 */
class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var achievementsAdapter: AchievementsAdapter

    companion object {
        /**
         * Creates an instance of HomeFragment
         */
        fun newInstance() = HomeFragment()
        val TAG: String = HomeFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_home
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Animating views.
         * */
        cvPendingRequests.animate().setDuration(2000).alpha(1f).start()
        cvAcceptedRequests.animate().setDuration(2000).alpha(1f).start()
        cvRejectedRequests.animate().setDuration(2000).alpha(1f).start()
        cvCompletedRelations.animate().setDuration(2000).alpha(1f).start()

        achievementsAdapter = AchievementsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, linearLayoutManager.orientation)

        rvAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(divider)
        }

        cvPendingRequests.setOnClickListener { gotoRequestsFragment() }
        cvAcceptedRequests.setOnClickListener { gotoRelationFragment() }
        cvRejectedRequests.setOnClickListener { gotoRequestsFragment() }
        cvCompletedRelations.setOnClickListener { gotoRequestsFragment() }
        rvAchievements.setOnClickListener { gotoRelationFragment() }

    }

    /**
     * Replaces the current fragment with the 'Relation' fragment.
     * */
    private fun gotoRelationFragment() {
        replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(),
                R.string.fragment_title_relation)
    }

    /**
     * Replaces the current fragment with the 'Requests' fragment.
     * */
    private fun gotoRequestsFragment() {
        replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(),
                R.string.fragment_title_requests)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        with(homeViewModel) {
            userStats.observe(viewLifecycleOwner, Observer { stats ->
                binding.stats = stats

                var total = stats.pendingRequests + stats.acceptedRequests + stats.rejectedRequests + stats.completedRelations
                binding.totalRequests = total

                sbPendingRequests.progress = if(total>0) (stats.pendingRequests * 100) / total else 0
                sbAcceptedRequests.progress = if(total>0) (stats.acceptedRequests * 100) / total else 0
                sbRejectedRequests.progress = if(total>0) (stats.rejectedRequests * 100) / total else 0
                sbCompletedRequests.progress = if(total>0) (stats.completedRelations * 100) / total else 0

                if (stats?.achievements?.isEmpty() != false) {
                    tvNoAchievements.visibility = View.VISIBLE
                    rvAchievements.visibility = View.GONE
                } else {
                    tvNoAchievements.visibility = View.GONE
                    rvAchievements.visibility = View.VISIBLE
                    achievementsAdapter.submitList(stats.achievements)
                }
            })

            message.observe(viewLifecycleOwner, Observer { message ->
                Snackbar.make(homeContainer, message.toString(), Snackbar.LENGTH_SHORT).show()
            })
        }

    }


}

