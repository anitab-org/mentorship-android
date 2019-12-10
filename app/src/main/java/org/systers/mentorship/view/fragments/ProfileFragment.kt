package org.systers.mentorship.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import android.view.*
import android.view.View.GONE
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentProfileBinding
import org.systers.mentorship.models.User
import org.systers.mentorship.viewmodels.ProfileViewModel
import java.io.ByteArrayOutputStream

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
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return fragmentProfileBinding.root
    }

    /**
     * It will contain the uri of the user's profile photo
     * */
    var imageuri:String?=null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        profileViewModel.successfulGet.observe(this, Observer {
            successful ->
            baseActivity.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    fragmentProfileBinding.user = profileViewModel.user
                    /**
                     * If user has a previously selected profile, then it will load that photo
                     * */
                    if(checkPermissionStatus()){
                        if (profileViewModel.user.photoUrl!=null){
                            imgUserAvatar.setImageURI(Uri.parse(profileViewModel.user.photoUrl))
                        }
                    }
                } else {
                    Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
                }
            }
        })
        baseActivity.showProgressDialog(getString(R.string.fetch_user_profile))
        profileViewModel.getProfile()

        /**
         * Declared for processing the result of UserProfile update status.
         * */
        profileViewModel.successfulUpdate.observe(this, Observer {
            successful ->
            baseActivity.hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context,"Profile updated successfully",Toast.LENGTH_LONG).show()
                } else {
                    Snackbar.make(fragmentProfileBinding.root, profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
                }
            }
        })

        /**
         * onClick method for the update profile button
         * */
        btnUpdateProfile.setOnClickListener {
            switchEnable(false, GONE)
            /**
             * Getting the updated values from the input fields.
             * */
            var user=User(
                    id = profileViewModel.user.id,
                    name = etUserName.text.toString(),
                    email = tiEmail.editText!!.text.toString(),
                    bio = tiBio.editText!!.text.toString(),
                    skills = tiSkills.editText!!.text.toString(),
                    interests = tiInterests.editText!!.text.toString(),
                    slackUsername = tiSlackUsername.editText!!.text.toString(),
                    location = tiLocation.editText!!.text.toString(),
                    occupation = tiOccupation.editText!!.text.toString(),
                    organization = tiOrganization.editText!!.text.toString(),
                    isAvailableToMentor = switchAvailableToMentor.isChecked,
                    needsMentoring = switchNeedMentorship.isChecked,
                    photoUrl = imageuri
            )
            /**
             * Username is unique, so it is updated only if it has been changed.
             * */
            var new_username=tiUsername.editText!!.text.toString()
            if (new_username != profileViewModel.user.username){
                user.username=new_username
            }
            /**
             * Updating the user profile
             * */
            profileViewModel.updateProfile(user)
        }

        /**
         * onClick method for the cancel button.
         * */
        btnCancelUpdateProfile.setOnClickListener{
            switchEnable(false, GONE)
            /**
             * Deletes the changes made and sets the data as of the old user profile.
             * */
            etUserName!!.setText(profileViewModel.user.name)
            tiUsername.editText!!.setText(profileViewModel.user.username)
            tiEmail.editText!!.setText(profileViewModel.user.email)
            tiBio.editText!!.setText(profileViewModel.user.bio)
            tiSlackUsername.editText!!.setText(profileViewModel.user.slackUsername)
            tiLocation.editText!!.setText(profileViewModel.user.location)
            tiOccupation.editText!!.setText(profileViewModel.user.occupation)
            tiOrganization.editText!!.setText(profileViewModel.user.organization)
            tiSkills.editText!!.setText(profileViewModel.user.skills)
            tiInterests.editText!!.setText(profileViewModel.user.interests)
            switchAvailableToMentor.isChecked= profileViewModel.user.isAvailableToMentor!!
            switchNeedMentorship.isChecked= profileViewModel.user.needsMentoring!!
            imgUserAvatar.setImageURI(Uri.parse(profileViewModel.user.photoUrl))
        }

        /**
         * onClick method for uploading image, starts the imageUpload process.
         * */
        imgCamera.setOnClickListener {
            var result=checkPermissionStatus()
            if (result){
                uploadProfileImage()
            }
        }
    }

    /**
     * request codes for intent and permission
     * */
    val INTENT_REQUEST_CODE=1
    val PERMISSION_REQUEST_CODE=5

    /**
     * Checks and returns the status of permissions by the user.
     * */
    private fun checkPermissionStatus():Boolean {

        if (ContextCompat.checkSelfPermission(this!!.activity!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this!!.activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            return true
        } else {

            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            return false
        }
    }

    /**
     * This method sets up the intent for the Image to be choosen, from gallery and camera.
     * The intentChooser merges up the two intents, and the activity is then called for both intents.
     * */
    private fun uploadProfileImage() {
        var camera_intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var gallery_intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery_intent.setType("image/*")
        var intent_chooser=Intent.createChooser(gallery_intent,"Choose from : ")
        intent_chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(camera_intent))
        startActivityForResult(intent_chooser,INTENT_REQUEST_CODE)
    }

    /**
     * This method checks the result of whether the user has granted permission or not.
     * */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0) {
                uploadProfileImage()
            }
        } else {
            Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * This method takes the result from the activity called through intentChooser, i.e. takes the image chosen by user
     * and transforms it into the required type to upload the image on the ImageView 'imgUserAvatar'
     * */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data!=null){

                /**
                 * If image is chosen from gallery (bundle==null), then there is no need to set up the URI of image.
                 * But if image is chosen from camera, it first stores it on user's device to set up the URI of image and
                 * then it uploads the created URI of the image.
                 * */
                var bundle=data!!.extras
                if (bundle==null){
                    imgUserAvatar.setImageURI(data.data)
                    imageuri=data.data.toString()
                }else{
                    var bitmap=bundle.get("data") as Bitmap
                    imgUserAvatar.setImageBitmap(bitmap)
                    /**
                     * Converting Bitmap to a URI string,
                     * storing image on user's device
                     * */
                    var baos=ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
                    imageuri= MediaStore.Images.Media.insertImage(context!!.contentResolver,bitmap,"Profile Photo",null)
                }
            }else{
                Toast.makeText(context, "Please choose an image", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Could not upload image.\nTry again", Toast.LENGTH_SHORT).show()
        }
    }

    var menuItemStatus:Boolean=true

    /**
     * This method switches the enabled state of all elements on Profile fragment according to the parameter passed.
     * */

    @SuppressLint("NewApi")
    private fun switchEnable(edit:Boolean, view:Int){

        menuItemStatus=!edit
        setHasOptionsMenu(true)
        activity!!.invalidateOptionsMenu()

        var color=if (edit) R.color.black else R.color.transparent

        etUserName.isEnabled=edit
        etUserName.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiUsername.isEnabled=edit
        tiUsername.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiEmail.isEnabled=edit
        tiEmail.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        switchAvailableToMentor.isClickable=edit

        switchNeedMentorship.isClickable=edit

        tiBio.isEnabled=edit
        tiBio.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiSlackUsername.isEnabled=edit
        tiSlackUsername.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiLocation.isEnabled=edit
        tiLocation.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiOccupation.isEnabled=edit
        tiOccupation.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiOrganization.isEnabled=edit
        tiOrganization.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiSkills.isEnabled=edit
        tiSkills.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        tiInterests.isEnabled=edit
        tiInterests.backgroundTintList= ColorStateList.valueOf(resources.getColor(color))

        imgCamera.visibility=view

        btnUpdateProfile.visibility=view

        btnCancelUpdateProfile.visibility=view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_my_profile, menu)
        var menuItem=menu!!.findItem(R.id.menu_settings)
        menuItem.setVisible(menuItemStatus)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit_profile -> {
                switchEnable(true,View.VISIBLE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        if (!menuItemStatus){
            menu!!.clear()
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        profileViewModel.successfulGet.removeObservers(activity!!)
        profileViewModel.successfulGet.value = null
    }
}
