package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_avatar_edit.*
import org.systers.mentorship.R

class AvatarEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_edit)
        btnEditAvatar.setOnClickListener {
            //TODO: intent to choose an avatar
        }
    }
}
