package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A Fragment class which other Fragments can extend from. It reduces boilerplate of
 * other fragments setting up their own views and  action bat title
 */
abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    /**
     * This function is responsible to return the layout resource id of the fragment,
     * so that the view can be set by the parent fragment
     * @return layout resource id of the fragment child view
     */
    protected abstract fun getLayoutResourceId() : Int
}
