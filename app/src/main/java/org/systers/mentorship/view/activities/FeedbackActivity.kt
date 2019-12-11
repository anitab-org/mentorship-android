package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_feedback.*
import org.systers.mentorship.R

class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        setTitle(R.string.fragment_title_feedback);
        btn.setOnClickListener {
            Toast.makeText(this, "Not implemented yet",Toast.LENGTH_SHORT ).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
