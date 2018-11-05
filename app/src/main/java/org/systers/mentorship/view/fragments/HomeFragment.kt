package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible for showing a welcoming message and show some statistics of the User
 * usage of the app
 */
class HomeFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of HomeFragment
         */
        fun newInstance() = HomeFragment()
        val TAG = HomeFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_home
    }
}
