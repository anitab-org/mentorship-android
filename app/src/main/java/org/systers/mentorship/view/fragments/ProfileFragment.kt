package org.systers.mentorship.view.fragments

import android.content.DialogInterface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.*
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.applicationClass
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.models.User
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
    private lateinit var userProfile: User
    private val profileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }
    override fun getLayoutResourceId(): Int = R.layout.fragment_profile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return fragmentProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        srlProfile.setOnRefreshListener { fetchNewest() }
        if(applicationClass.user!=null) {
            fragmentProfileBinding.user = applicationClass.user
        }
        else{
            fetchNewest()
            profileViewModel.successfulGet.observe(this, Observer {
                successful ->
                srlProfile.isRefreshing = false
                if (successful != null) {
                    if (successful) {
                        fragmentProfileBinding.user = profileViewModel.user
                    } else {
                        Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                                Snackbar.LENGTH_LONG).show()
                    }
                }
            })

        }


        profileViewModel.getUserProfileFromDatabase.observe(this, Observer { user->
            if(user!=null){
                srlProfile.isRefreshing = false
                fragmentProfileBinding.user = user
                userProfile = user
            }else{
                fetchNewest()
                profileViewModel.successfulGet.observe(this, Observer {
                    successful ->
                    srlProfile.isRefreshing = false
                    if (successful != null) {
                        if (successful) {
                            fragmentProfileBinding.user = profileViewModel.user
                            insertDataToDatabase(profileViewModel.user)
                        } else {
                            Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                                    Snackbar.LENGTH_LONG).show()
                        }
                    }
                })
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_my_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                if(fragmentProfileBinding.user != null){
                    var editProfileFragment:EditProfileFragment = EditProfileFragment.newInstance(applicationClass.user!!)
                    var editProfileFragment:EditProfileFragment = EditProfileFragment.newInstance(userProfile)
                    editProfileFragment.setOnDismissListener(DialogInterface.OnDismissListener {
                        fetchNewest()
                    })
                    editProfileFragment.show(fragmentManager, getString(R.string.fragment_title_edit_profile))
                }
                true
            }
            R.id.menu_refresh -> {
                fetchNewest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchNewest() {
        if(applicationClass.user!=null){
            srlProfile.isRefreshing = false
        }else{
            srlProfile.isRefreshing = true
            profileViewModel.getProfile()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        profileViewModel.successfulGet.removeObservers(activity!!)
        profileViewModel.successfulGet.value = null
    }

    private fun insertDataToDatabase(userprofile: User){
        profileViewModel.storeUserProfile(userprofile)
    }
    private fun updateUserProfile(userProfile: User){
        profileViewModel.updateProfile(userProfile)
    }
}
