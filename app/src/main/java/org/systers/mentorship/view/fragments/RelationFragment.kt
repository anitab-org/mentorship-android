package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible present the current mentorship relation of the user details
 */
class RelationFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of RelationFragment
         */
        fun newInstance() = RelationFragment()
        val TAG = RelationFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_relation
    }
}
