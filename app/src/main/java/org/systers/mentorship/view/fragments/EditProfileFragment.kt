package org.systers.mentorship.view.fragments


import android.app.Dialog
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
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.media.Image
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.util.jar.Manifest


/**
 * The fragment is responsible for editing the User's profile
 */
class EditProfileFragment: DialogFragment() {

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

        editProfileDialog.imgUserAvatar.setOnClickListener {
            selectImage()
        }

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
            if (errors.isEmpty()) {
                profileViewModel.updateProfile(editProfileBinding.user!!)
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(context!!, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(WRITE_EXTERNAL_STORAGE),
                    1001)
            println("Not granted.")
        } else {
            println("Permission is granted.")
        }
    }

    private fun selectImage() {
        checkPermissions()
        // Create intent to Open Image applications like Gallery, Google Photos
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // Start the Intent
        startActivityForResult(galleryIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val editProfileDialog = dialog as AlertDialog

        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                // Get the cursor
                val cursor = context!!.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null)
                // Move to first row
                cursor.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val imgDecodableString = cursor.getString(columnIndex)
                cursor.close()
                // Set the Image in ImageView after decoding the String
                val imgView = editProfileDialog.imgUserAvatar as ImageView
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString))

            } else {
                Toast.makeText(activity, R.string.notpickedimage,
                        Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(activity, R.string.error_something_went_wrong, Toast.LENGTH_LONG)
                    .show()
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
}
