package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.viewmodels.ProfileViewModel

/**
 * The fragment is responsible for showing the User's profile
 */
class ProfileFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of ProfileFragment
         */
        fun newInstance() = ProfileFragment()

        val TAG: String = ProfileFragment::class.java.simpleName
    }

    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_profile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container,
                false)
        return fragmentProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        profileViewModel = ViewModelProvider(baseActivity).get(ProfileViewModel::class.java)
        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            baseActivity.hideProgressDialog()
            if (user != null) {
                fragmentProfileBinding.user = user
            } else {
                Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                        Snackbar.LENGTH_LONG).show()
            }
        })
        baseActivity.showProgressDialog(getString(R.string.fetch_user_profile))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_my_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                EditProfileFragment.newInstance(fragmentProfileBinding.user!!).show(parentFragmentManager,
                        getString(R.string.fragment_title_edit_profile))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
