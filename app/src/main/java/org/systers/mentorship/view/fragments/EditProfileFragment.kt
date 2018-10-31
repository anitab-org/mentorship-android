package org.systers.mentorship.view.fragments


import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.viewmodels.ProfileViewModel

/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

    companion object {
        private lateinit var user: User
        /**
         * Creates an instance of EditProfileFragment
         */
        fun newInstance(user: User): EditProfileFragment {
            this.user = user
            return EditProfileFragment()
        }
    }

    private lateinit var newUser: User

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        profileViewModel.successfulUpdate.observe(this, Observer {
            successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    profileViewModel.user = user
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
        val dialog = LayoutInflater.from(context).inflate(R.layout.fragment_edit_profile, null)

        //TODO: Populate view with existing details from [user] object

        val dialogBuilder = AlertDialog.Builder(context!!)
        dialogBuilder.setView(dialog)
        dialogBuilder.setTitle(R.string.fragment_title_edit_profile)
        dialogBuilder.setPositiveButton(getString(R.string.save), null)
        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }

        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()

        val editProfileDialog = dialog as AlertDialog
        editProfileDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            //TODO: Construct the object newUser by getting the new information
            if (newUser == user) {
                dismiss()
            } else {
                profileViewModel.updateProfile(newUser)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulUpdate.value = null
    }
}
