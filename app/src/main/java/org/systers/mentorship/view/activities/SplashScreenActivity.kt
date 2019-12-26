package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.utils.getUnixTimestampInMilliseconds

/**
 * This activity will show the organisation logo for sometime and then start the next activity
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var SPLASH_DISPLAY_LENGTH: Long = 1000
    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var intent =
                Intent(this,
                        if (preferenceManager.authToken.isEmpty()) LoginActivity::class.java
                        else MainActivity::class.java)

        if (getUnixTimestampInMilliseconds(preferenceManager.authExpiry) < System.currentTimeMillis())
            if (getUnixTimestampInMilliseconds(preferenceManager.refreshExpiry) < System.currentTimeMillis()) {
                intent = Intent(this, LoginActivity::class.java)
                Toast.makeText(applicationContext, R.string.token_expired, Toast.LENGTH_LONG).show()
            }

        runnable = Runnable {
            startActivity(intent)
            finish()
        }
        handler = Handler()
        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGTH)
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(runnable)
    }
}
