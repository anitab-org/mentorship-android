package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible for showing the User's profile and be able to edit it
 */
class ProfileFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of ProfileFragment
         */
        fun newInstance() = ProfileFragment()
        val TAG = ProfileFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_profile
    }
}
