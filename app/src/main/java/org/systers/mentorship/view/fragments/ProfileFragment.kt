package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
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
    private lateinit var profileMenu: Menu
    private lateinit var currentUser: User
    private var inEditMode: Boolean = false
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

        srlProfile.setOnRefreshListener {
            if (!inEditMode)
                fetchNewest()
            else
                srlProfile.isRefreshing = false

        }

        profileViewModel.successfulGet.observe(this, Observer { successful ->
            srlProfile.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    currentUser = profileViewModel.user.copy()
                    fragmentProfileBinding.user = profileViewModel.user
                } else {
                    Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
                }
            }
        })

        profileViewModel.successfulUpdate.observe(this, Observer { successful ->
            srlProfile.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    disableEdit()
                } else {
                    Toast.makeText(activity!!,profileViewModel.message,Toast.LENGTH_LONG).show()
                }
            }


        })
        fetchNewest()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        profileMenu = menu
        inflater.inflate(R.menu.menu_my_profile, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        if (menu != null) {
            if (inEditMode) {
                menu.findItem(R.id.menu_exit_edit).isVisible = true
                menu.findItem(R.id.menu_save_profile).isVisible = true
                menu.findItem(R.id.menu_edit_profile).isVisible = false
            } else {
                menu.findItem(R.id.menu_exit_edit).isVisible = false
                menu.findItem(R.id.menu_save_profile).isVisible = false
                menu.findItem(R.id.menu_edit_profile).isVisible = true
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                if (fragmentProfileBinding.user != null) {
                    inEditMode = true
                    enableEdit()
                    onPrepareOptionsMenu(menu = profileMenu)
                }
                true
            }
            R.id.menu_exit_edit -> {
                if (fragmentProfileBinding.user != null) {
                    disableEdit()
                    fetchNewest()
                }
                true
            }
            R.id.menu_save_profile -> {
                if (fragmentProfileBinding.user != null) {
                    onSave()
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
        srlProfile.isRefreshing = true
        profileViewModel.getProfile()
    }

    private fun onSave() {
        val errors = validateProfileInput(fragmentProfileBinding.user?.name?.trim())

        with(fragmentProfileBinding.etUserName) {
            this.error = when (errors.firstOrNull()) {
                is EditProfileFragmentErrorStates.EmptyNameError ->
                    context.getString(R.string.error_empty_name)
                is EditProfileFragmentErrorStates.NameTooShortError -> {
                    val minLength = resources.getInteger(R.integer.min_name_length)
                    context.getString(R.string.error_name_too_short, minLength)
                }
                is EditProfileFragmentErrorStates.NameTooLongError -> {
                    val maxLength = resources.getInteger(R.integer.max_name_length)
                    context.getString(R.string.error_name_too_long, maxLength)
                }
                else -> {
                    null
                }
            }
        }
        if (currentUser != fragmentProfileBinding.user && errors.isEmpty()) {
            srlProfile.isRefreshing = true
            profileViewModel.updateProfile(fragmentProfileBinding.user!!)
        } else if (currentUser == fragmentProfileBinding.user && errors.isEmpty()) {
            disableEdit()
        }
    }

    private fun enableEdit() {
        fragmentProfileBinding.etUserName.isEnabled = true
        fragmentProfileBinding.switchAvailableToMentor.isEnabled = true
        fragmentProfileBinding.tiSkills.isEnabled = true
        fragmentProfileBinding.switchNeedMentorship.isEnabled = true
        fragmentProfileBinding.tiBio.isEnabled = true
        fragmentProfileBinding.tiInterests.isEnabled = true
        fragmentProfileBinding.tiLocation.isEnabled = true
        fragmentProfileBinding.tiOccupation.isEnabled = true
        fragmentProfileBinding.tiOrganization.isEnabled = true
        fragmentProfileBinding.tiSlackUsername.isEnabled = true
    }

    private fun disableEdit() {
        inEditMode = false
        onPrepareOptionsMenu(profileMenu)
        fetchNewest()
        fragmentProfileBinding.etUserName.isEnabled = false
        fragmentProfileBinding.tiSkills.isEnabled = false
        fragmentProfileBinding.switchAvailableToMentor.isEnabled = false
        fragmentProfileBinding.switchNeedMentorship.isEnabled = false
        fragmentProfileBinding.tiBio.isEnabled = false
        fragmentProfileBinding.tiInterests.isEnabled = false
        fragmentProfileBinding.tiLocation.isEnabled = false
        fragmentProfileBinding.tiOccupation.isEnabled = false
        fragmentProfileBinding.tiOrganization.isEnabled = false
        fragmentProfileBinding.tiSlackUsername.isEnabled = false
    }

    private fun validateProfileInput(name: String?): Array<EditProfileFragmentErrorStates> {

        var errors = arrayOf<EditProfileFragmentErrorStates>()

        if (name.isNullOrEmpty()) errors += EditProfileFragmentErrorStates.EmptyNameError

        if (name?.length ?: 0 < resources.getInteger(R.integer.min_name_length)) {
            errors += EditProfileFragmentErrorStates.NameTooShortError
        }

        if (name?.length ?: 0 > resources.getInteger(R.integer.max_name_length)) {
            errors += EditProfileFragmentErrorStates.NameTooLongError
        }
        return errors
    }

    override fun onDestroy() {
        super.onDestroy()
        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulGet.removeObservers(activity!!)
        profileViewModel.successfulGet.value = null
        profileViewModel.successfulUpdate.value = null
    }
}
