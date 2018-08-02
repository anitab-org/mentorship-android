package org.systers.mentorship.view.fragments

import org.systers.mentorship.R

/**
 * The fragment is responsible for showing the all mentorship requests
 * and filtered lists such as for pending requests and past relations and requests
 */
class RequestsFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of RequestsFragment
         */
        fun newInstance() = RequestsFragment()
        val TAG = RelationFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_requests
    }
}
