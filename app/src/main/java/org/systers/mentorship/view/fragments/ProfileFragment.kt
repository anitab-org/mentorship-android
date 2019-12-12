package org.systers.mentorship.view.fragments

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
import org.systers.mentorship.utils.toUser
import org.systers.mentorship.utils.toUserCopy
import org.systers.mentorship.view.activities.SettingsActivity
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
    private lateinit var menu: Menu

    override fun getLayoutResourceId(): Int = R.layout.fragment_profile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        setHasOptionsMenu(true)
        return fragmentProfileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        profileViewModel.successfulGet.observe(this, Observer { successful ->
            baseActivity.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    fragmentProfileBinding.user = profileViewModel.user.toUser()
                } else {
                    Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
                }
            }
        })
        profileViewModel.successfulUpdate.observe(this, Observer { successful ->
            if (successful != null) {
                if (!successful)
                    Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
            }
        })

        baseActivity.showProgressDialog(getString(R.string.fetch_user_profile))
        profileViewModel.getProfile()

        profileViewModel.edit.observe(this, Observer { edit ->
            fragmentProfileBinding.edit = edit
        })

        fragmentProfileBinding.fabEditAvatar.setOnClickListener {
            // TODO: Add "Edit avatar" function here
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        inflater.inflate(R.menu.menu_my_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_edit_profile -> {
                if (profileViewModel.edit.value == true) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(R.string.fragment_title_edit_profile)
                    builder.setMessage(getString(R.string.sure_save_changes))
                    builder.setNegativeButton(getString(R.string.no), null)
                    builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
                        if (saveUpdates()) {
                            menu.findItem(R.id.menu_settings).apply {
                                setIcon(R.drawable.ic_settings_white_24dp)
                                setTitle(R.string.popup_menu_settings)
                            }
                            item.apply {
                                setIcon(R.drawable.ic_edit_white_24dp)
                                setTitle(R.string.edit_profile)
                            }
                            fragmentProfileBinding.fabEditAvatar.hide()
                            profileViewModel.edit.value = false
                        }
                    }
                    builder.show()
                } else {
                    profileViewModel.edit.value = true
                    fragmentProfileBinding.fabEditAvatar.show()
                    menu.findItem(R.id.menu_settings).apply {
                        setIcon(R.drawable.ic_cancel_white_24dp)
                        title = getString(R.string.cancel)
                    }
                    item.apply {
                        setIcon(R.drawable.ic_check_white_24dp)
                        title = getString(R.string.apply_changes)
                    }
                }
                true
            }
            R.id.menu_settings -> {
                if (profileViewModel.edit.value == true) {
                    menu.findItem(R.id.menu_edit_profile).apply {
                        setIcon(R.drawable.ic_edit_white_24dp)
                        setTitle(R.string.edit_profile)
                    }
                    item.apply {
                        setIcon(R.drawable.ic_settings_white_24dp)
                        setTitle(R.string.popup_menu_settings)
                    }
                    profileViewModel.edit.value = false
                    fragmentProfileBinding.user = profileViewModel.user.toUser()
                    fragmentProfileBinding.fabEditAvatar.hide()
                    fragmentProfileBinding.etUserName.error = null
                } else {
                    startActivity(Intent(requireContext(), SettingsActivity::class.java))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        menu.findItem(R.id.menu_edit_profile).setIcon(R.drawable.ic_edit_white_24dp)
        menu.findItem(R.id.menu_settings).setIcon(R.drawable.ic_settings_white_24dp)
        profileViewModel.edit.value = false
        profileViewModel.message = ""
        profileViewModel.successfulUpdate.value = true
        fragmentProfileBinding.fabEditAvatar.hide()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        profileViewModel.successfulGet.removeObservers(activity!!)
        profileViewModel.successfulGet.value = null
    }

    private fun saveUpdates(): Boolean {
        val errors = validateProfileInput(fragmentProfileBinding.user?.name?.trim())

        with(fragmentProfileBinding.etUserName) {
            this.error = when (errors.firstOrNull()) {
                is EditProfileFragmentErrorStates.NameTooShortError -> {
                    val minLength = resources.getInteger(R.integer.min_name_length)
                    context.getString(R.string.error_name_too_short, minLength)
                }
                is EditProfileFragmentErrorStates.NameTooLongError -> {
                    val maxLength = resources.getInteger(R.integer.max_name_length)
                    context.getString(R.string.error_name_too_long, maxLength)
                }
                else -> null
            }
        }
        if (errors.isEmpty() && fragmentProfileBinding.user != null) {
            profileViewModel.updateProfile(fragmentProfileBinding.user!!.copy(id = null, username = null, email = null))
            profileViewModel.user = fragmentProfileBinding.user!!.toUserCopy()
            return true
        }
        return false
    }

    private fun validateProfileInput(name: String?): Array<EditProfileFragmentErrorStates> {
        var errors = arrayOf<EditProfileFragmentErrorStates>()

        if (name?.length ?: 0 < resources.getInteger(R.integer.min_name_length))
            errors += EditProfileFragmentErrorStates.NameTooShortError

        if (name?.length ?: 0 > resources.getInteger(R.integer.max_name_length))
            errors += EditProfileFragmentErrorStates.NameTooLongError

        return errors
    }

}
