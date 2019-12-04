package org.systers.mentorship.view.fragments


import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_edit_profile.*
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
        editProfileDialog.profilePictureButton.setOnClickListener{selectImage()}
        /* "upload profile picture" button working
        1. xml names: imgUserAvatar(ImageView), profilePictureButton(FloatingActionButton)
        2. listener selectImage() attached to button in onResume() function
        3. selectImage(): Checks for CAMERA and WRITE_EXTERNAL_STORAGE permissions. It then displays
           AlertDialog with 3 options- take photo, go to gallery and cancel. These activate respective
           intents.
        4. onActivityResult() is triggered when intents are executed. It updates image in imgUserAvatar.
         */

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
    private fun selectImage(){
        if (checkPermissions()){
            val options = arrayOf<CharSequence>(getString(R.string.select_image_camera),
                    getString(R.string.select_image_gallery), getString(R.string.select_image_cancel))
            val builder= AlertDialog.Builder(context!!)
            builder.setItems(options){
                dialog,item ->
                when(options[item]){
                    getString(R.string.select_image_camera)-> {
                        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePicture, 0)
                    }
                    getString(R.string.select_image_gallery)-> {
                        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(pickPhoto, 1)
                    }
                    getString(R.string.select_image_cancel)-> {
                        dialog.dismiss()
                    }
                }
            }
            builder.show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            val editProfileDialog = dialog as AlertDialog
            val imageView = editProfileDialog.imgUserAvatar as ImageView
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!.get("data") as Bitmap
                    imageView.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor = context!!.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null)

                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                            val imgDecodableString = cursor.getString(columnIndex)
                            cursor.close()
                            imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString))
                        }
                    }
                }
            }
        }
    }
    private fun checkPermissions(): Boolean {
        if ((ContextCompat.checkSelfPermission(context!!, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(context!!, CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(activity!!, arrayOf(WRITE_EXTERNAL_STORAGE, CAMERA),
                    1001)
            println(getString(R.string.permission_granted))
            return false
        } else {
            println(getString(R.string.permission_rejected))
        }
        return true
    }
}
