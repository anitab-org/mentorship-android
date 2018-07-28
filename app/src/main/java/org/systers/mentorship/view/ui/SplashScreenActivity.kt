package org.systers.mentorship.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import org.systers.mentorship.R

/**
 * This activity will show the organisation logo for sometime and then start the next activity
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var SPLASH_DISPLAY_LENGTH: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intent = Intent(this, LoginActivity::class.java)

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
