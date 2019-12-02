package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_feedback.*
import org.systers.mentorship.R

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        supportActionBar?.title = getString(R.string.feedback)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        sendButton.setOnClickListener {
            if (emailEtFB.text.toString().isEmailValid()) {
                sendFeedback()
            } else {
                Toast.makeText(this, R.string.entervalidemail, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendFeedback(){
        Toast.makeText(this,R.string.feedbacksent, Toast.LENGTH_SHORT).show()
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}
