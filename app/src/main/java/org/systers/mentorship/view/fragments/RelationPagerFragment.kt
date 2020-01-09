package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.fragment_relation.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.BaseUrl.GET_CURRENT_RELATION_URL
import org.systers.mentorship.utils.md5
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.RelationPagerAdapter
import org.systers.mentorship.viewmodels.RelationViewModel
import java.io.File

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

    private lateinit var relationViewModel: RelationViewModel
    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_relation
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        srlRelation.setOnRefreshListener {
            //deleting the cache file
            File("${context?.cacheDir?.absolutePath}/${GET_CURRENT_RELATION_URL.md5()}.1").delete()

            activityCast.showProgressDialog(getString(R.string.fetching_current_relation))
            relationViewModel.getCurrentRelationDetails()
        }

        relationViewModel = ViewModelProviders.of(this).get(RelationViewModel::class.java)
        relationViewModel.successfulGet.observe(this, Observer {
            successful ->
            srlRelation.isRefreshing = false
            activityCast.hideProgressDialog()
            if (successful != null) {
                updateView(relationViewModel.mentorshipRelation)
                if (!successful) {
                    view?.let {
                        Snackbar.make(it, relationViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        activityCast.showProgressDialog(getString(R.string.fetching_current_relation))
        relationViewModel.getCurrentRelationDetails()
    }

    private fun updateView(mentorshipRelation: Relationship) {
        if (mentorshipRelation.id == 0) {
            tvNoCurrentRelation.visibility = View.VISIBLE
            tlMentorshipRelation.visibility = View.GONE
            vpMentorshipRelation.visibility = View.GONE
            baseActivity.tlMentorshipRelation.removeAllTabs()
        } else {
            tvNoCurrentRelation.visibility = View.GONE
            tlMentorshipRelation.visibility = View.VISIBLE
            vpMentorshipRelation.visibility = View.VISIBLE
            vpMentorshipRelation.adapter = RelationPagerAdapter(childFragmentManager, mentorshipRelation)
            tlMentorshipRelation.setupWithViewPager(vpMentorshipRelation)
        }
    }
}
