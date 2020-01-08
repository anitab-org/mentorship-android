package org.systers.mentorship.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.systers.mentorship.R
import org.systers.mentorship.view.activities.BaseActivity

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

    /**
     * Instance of parent activity as [BaseActivity]
     */
    val baseActivity: BaseActivity
        get() = activity as BaseActivity

    /**
     * The [fragment] is added to the container view with id [containerId]. The operation is
     * performed by the FragmentManager.
     */
    fun replaceFragment(containerId: Int, fragment: Fragment, title: Int) {
        activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.inout_enter,R.anim.inout_exit)
                ?.replace(containerId, fragment, getString(title))
                ?.commit()
        baseActivity.supportActionBar?.setTitle(title)
    }
}
