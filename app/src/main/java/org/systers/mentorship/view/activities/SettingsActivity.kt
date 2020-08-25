package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_settings.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment
import org.systers.mentorship.viewmodels.SettingsViewModel

class SettingsActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_logout)
            builder.setMessage(R.string.confirm_logout_msg)
            builder.setPositiveButton(R.string.logout) { _, _ ->
                preferenceManager.clear()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
            builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        tvResetPassword.setOnClickListener {
            ChangePasswordFragment.newInstance().show(supportFragmentManager, null)
        }

        tvAbout.setOnClickListener {
            startActivity(Intent(baseContext, AboutActivity::class.java))
        }

        tvFeedback.setOnClickListener {
            startActivity(Intent(baseContext, FeedbackActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
