package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.systers.mentorship.utils.PreferenceManager

/**
 * This activity will show the organisation logo for sometime and then start the next activity
 */
class SplashScreenActivity : AppCompatActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = if (preferenceManager.authToken.isEmpty()) {
            Intent(this, LoginActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

}
