package org.systers.mentorship.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.*
import android.widget.Toast
import org.systers.mentorship.R
import org.systers.mentorship.viewmodels.MyProfileViewModel
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.view.activities.MainActivity

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

    private lateinit var viewDataBinding: FragmentProfileBinding
    private lateinit var myProfileViewModel: MyProfileViewModel

    private lateinit var editMenuItem: MenuItem
    private val activityCast by lazy { activity as MainActivity }

    override fun getLayoutResourceId(): Int = R.layout.fragment_profile

    /**
     * Overriding [BaseFragment] onCreateView since in this fragment its used DataBinding
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        myProfileViewModel = ViewModelProviders.of(this).get(MyProfileViewModel::class.java)
        viewDataBinding.vm = myProfileViewModel

        myProfileViewModel.getProfileSuccessful.observe(this, Observer {
            successful ->
            activityCast.hideProgressDialog()
            if (successful != null) {
                if (!successful) {
                    view?.let {
                        Snackbar.make(it, myProfileViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        myProfileViewModel.updateProfileSuccessful.observe(this, Observer {
            successful ->
            activityCast.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    myProfileViewModel.toggleEditionMode(editMenuItem)
                    Toast.makeText(activityCast, myProfileViewModel.message, Toast.LENGTH_LONG).show()
                } else {
                    view?.let {
                        Snackbar.make(it, myProfileViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        activityCast.showProgressDialog(getString(R.string.updating_profile))
        myProfileViewModel.getProfile()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_my_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)

        editMenuItem = menu.findItem(R.id.menu_edit_profile)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                myProfileViewModel.toggleEditionMode(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
