package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_avatar_edit.*
import org.systers.mentorship.R

class AvatarEditActivity : AppCompatActivity() {

    private var resultUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_edit)
        //TODO: Load image from the database into ImageView
        // Glide.with(this).load(avatar).into(imgUserAvatarBig)
        btnEditAvatar.setOnClickListener {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                resultUri = result.uri
                Glide.with(this).load(resultUri).into(imgUserAvatarBig)
                //TODO: upload the image
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                result.error.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val data = Intent()
        if (resultUri != null) {
            data.putExtra("uri", resultUri)
            setResult(Activity.RESULT_OK, data)
        } else
            setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
