package org.systers.mentorship.view.fragments


import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentEditProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.EditProfileFragmentErrorStates
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.viewmodels.ProfileViewModel

/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

    companion object {
        private lateinit var tempUser: User
        val IMAGE_PICK_CODE : Int = 1000
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
        profileViewModel.successfulUpdate.observe(this, Observer { successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context, getText(R.string.update_successful), Toast.LENGTH_LONG).show()
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
        editProfileBinding.imgUserAvatar.setOnClickListener{
            pickImageFromGallery()
        }
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

        editProfileDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val errors = validateProfileInput(editProfileBinding.user?.name?.trim())

            with(editProfileBinding.tiName) {
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
                        this.isErrorEnabled = false
                        null
                    }
                }
            }
            if (currentUser != editProfileBinding.user && errors.isEmpty()) {
                profileViewModel.updateProfile(editProfileBinding.user!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulUpdate.removeObservers(activity!!)
        profileViewModel.successfulUpdate.value = null
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

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            editProfileBinding.imgUserAvatar.setImageURI(data?.data)
        }
    }
}
