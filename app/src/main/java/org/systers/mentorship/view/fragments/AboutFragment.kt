package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible for showing the About page.
 */
class AboutFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of AboutFragment
         */
        fun newInstance() = AboutFragment()
        val TAG = AboutFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_about
    }
}
