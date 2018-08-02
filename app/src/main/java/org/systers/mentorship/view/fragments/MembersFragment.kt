package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible for showing all the members of the system in a list format
 */
class MembersFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of MembersFragment
         */
        fun newInstance() = MembersFragment()
        val TAG = MembersFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_members
    }
}
