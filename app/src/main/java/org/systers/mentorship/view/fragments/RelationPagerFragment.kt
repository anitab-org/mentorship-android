package org.systers.mentorship.view.fragments

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_relation.*
import org.systers.mentorship.R
import org.systers.mentorship.view.adapters.RelationPagerAdapter

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

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_relation
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vpMentorshipRelation.adapter = RelationPagerAdapter(childFragmentManager)
        tlMentorshipRelation.setupWithViewPager(vpMentorshipRelation)
    }
}
