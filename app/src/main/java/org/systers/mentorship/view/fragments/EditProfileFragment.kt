package org.systers.mentorship.view.fragments


import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Switch
import android.widget.Toast
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentEditProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.viewmodels.ProfileViewModel

/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

    private lateinit var mentorSwitch: Switch
    private lateinit var menteeSwitch: Switch

    companion object {
        private lateinit var tempUser: User
        /**
         * Creates an instance of EditProfileFragment
         */
        fun newInstance(user: User): EditProfileFragment {
            tempUser = user.copy(id = null, username = null, email = null)
            return EditProfileFragment()
        }
    }

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var editProfileBinding: FragmentEditProfileBinding

    private lateinit var currentUser: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        profileViewModel.successfulUpdate.observe(this, Observer {
            successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context,getText(R.string.update_successful),Toast.LENGTH_LONG).show()
                    profileViewModel.getProfile()
                    dismiss()
                } else {
                    Toast.makeText(activity, profileViewModel.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        isCancelable = false
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        editProfileBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.fragment_edit_profile, null, false)

        editProfileBinding.user = tempUser.copy()
        currentUser = tempUser.copy()

        val dialogBuilder = AlertDialog.Builder(context!!)
        dialogBuilder.setView(editProfileBinding.root)
        dialogBuilder.setTitle(R.string.fragment_title_edit_profile)
        dialogBuilder.setPositiveButton(getString(R.string.save), null)
        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }

        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()

        val editProfileDialog = dialog as AlertDialog

        mentorSwitch = editProfileBinding.switchAvailableToMentor.findViewById(R.id.switchAvailableToMentor)
        menteeSwitch = editProfileBinding.switchNeedsMentoring.findViewById(R.id.switchNeedsMentoring)

        mentorSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            editProfileBinding.user?.isAvailableToMentor = isChecked
        }

        menteeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            editProfileBinding.user?.needsMentoring = isChecked
        }

        editProfileDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // TODO: Add validation
            if (currentUser != editProfileBinding.user) {
                profileViewModel.updateProfile(editProfileBinding.user!!)
            } else {
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulUpdate.value = null
    }
}
