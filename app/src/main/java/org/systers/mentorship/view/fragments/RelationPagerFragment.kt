package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ActivityMainBinding
import org.systers.mentorship.databinding.FragmentRelationBinding
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.RelationPagerAdapter
import org.systers.mentorship.viewmodels.RelationViewModel

/**
 * This fragment is instantiated per each tab from the RelationFragment ViewPager
 */
class RelationPagerFragment : BaseFragment() {

    private var _relationBinding: FragmentRelationBinding? = null
    private val relationBinding get() = _relationBinding!!

    companion object {
        /**
         * Creates an instance of [RelationPagerFragment]
         */
        fun newInstance() = RelationPagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _relationBinding = FragmentRelationBinding.inflate(inflater,container,false)
        return relationBinding.root
    }

    private val relationViewModel by lazy {
        ViewModelProviders.of(this).get(RelationViewModel::class.java)
    }
    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_relation
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        relationBinding.srlRelation.setOnRefreshListener { fetchNewest() }

        relationViewModel.successfulGet.observe(this, Observer {
            successfull ->
            relationBinding.srlRelation.isRefreshing = false
            if (successfull != null) {
                if (successfull) {
                    updateView(relationViewModel.mentorshipRelation)
                } else {
                    view?.let {
                        Snackbar.make(it, relationViewModel.message, Snackbar.LENGTH_LONG).show()
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

    private fun fetchNewest() {
        relationBinding.srlRelation.isRefreshing = true
        relationViewModel.getCurrentRelationDetails()
    }

    private fun updateView(mentorshipRelation: Relationship) {
        relationBinding.apply {
            if (mentorshipRelation.mentor == null) {

                tvNoCurrentRelation.visibility = View.VISIBLE
                tvFindPeopleBtn.visibility = View.VISIBLE
                tlMentorshipRelation.visibility = View.GONE
                vpMentorshipRelation.visibility = View.GONE
                tlMentorshipRelation.removeAllTabs()
                tvFindPeopleBtn.setOnClickListener {
                    baseActivity.findViewById<BottomNavigationView>(R.id.bottomNavigation).selectedItemId = R.id.navigation_members
                    baseActivity.replaceFragment(R.id.contentFrame, MembersFragment.newInstance(), R.string.navigation_title_members)
                }

            } else {
                tvNoCurrentRelation.visibility = View.GONE
                tvFindPeopleBtn.visibility = View.GONE
                tlMentorshipRelation.visibility = View.VISIBLE
                vpMentorshipRelation.visibility = View.VISIBLE
                vpMentorshipRelation.adapter = RelationPagerAdapter(childFragmentManager, mentorshipRelation)
                tlMentorshipRelation.setupWithViewPager(vpMentorshipRelation)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _relationBinding = null
    }
}
