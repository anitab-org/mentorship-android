package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_relation.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.RelationPagerAdapter
import org.systers.mentorship.viewmodels.RelationViewModel

/**
 * This fragment is instantiated per each tab from the RelationFragment ViewPager
 */
class RelationPagerFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [RelationPagerFragment]
         */
        fun newInstance() = RelationPagerFragment()
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
        srlRelation.setOnRefreshListener { fetchNewest() }

        relationViewModel.successfulGet.observe(this, Observer {
            successfull ->
            srlRelation.isRefreshing = false
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
        srlRelation.isRefreshing = true
        relationViewModel.getCurrentRelationDetails()
    }

    private fun updateView(mentorshipRelation: Relationship) {
        if (mentorshipRelation.mentor == null) {
            tvNoCurrentRelation.visibility = View.VISIBLE
            tvFindPeopleBtn.visibility = View.VISIBLE
            tlMentorshipRelation.visibility = View.GONE
            vpMentorshipRelation.visibility = View.GONE
            baseActivity.tlMentorshipRelation.removeAllTabs()
            tvFindPeopleBtn.setOnClickListener{
                baseActivity.bottomNavigation.selectedItemId = R.id.navigation_members
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
