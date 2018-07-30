package org.systers.mentorship.view.activities

import android.os.Bundle
import org.systers.mentorship.R

/**
 * This activity has the bottom navigation bra which allows the user to switch between fragments
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
