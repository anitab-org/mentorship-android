package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.credentials.Credentials
import kotlinx.android.synthetic.main.activity_settings.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment

class SettingsActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_logout)
            builder.setMessage(R.string.confirm_logout_msg)
            builder.setPositiveButton(R.string.logout) { _, _ ->
                preferenceManager.clear()
                val mCredentialsClient = Credentials.getClient(this)
                mCredentialsClient.disableAutoSignIn() //to prevent loop
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
            startActivity(Intent(baseContext,AboutActivity::class.java))
        }
        //TODO: delete smart lock credentials when account deleted
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
