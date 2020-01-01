package org.systers.mentorship.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_settings.*
import org.systers.mentorship.MentorshipApplication.Companion.credentialsClient
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment
import org.systers.mentorship.viewmodels.ProfileViewModel

class SettingsActivity : BaseActivity() {

    companion object {
        lateinit var instance: SettingsActivity
    }

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        instance = this

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.successfulGet.observe(this, Observer { successful ->
            if (successful != null && successful)
                user = profileViewModel.user
        })
        profileViewModel.getProfile()

        tvLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_logout)
            builder.setMessage(R.string.confirm_logout_msg)
            builder.setPositiveButton(R.string.logout) { _, _ ->
                // we don't want the same credentials to be used, as user might
                // want to choose another account
                credentialsClient.disableAutoSignIn()
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
            ChangePasswordFragment.newInstance(user.email, user.username)
                    .show(supportFragmentManager, null)
        }
        tvAbout.setOnClickListener {
            startActivity(Intent(baseContext, AboutActivity::class.java))
        }
        /*
        tvDeleteAccount.setOnClickListener {
            // TODO: Add code to get credential either using server or Credentials API
            credentialsClient.delete(credential)
                    .addOnCompleteListener {

                    }
        }
       */
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.RC_SAVE && resultCode == Activity.RESULT_OK)
            Toast.makeText(this, R.string.credentials_saved_successfully,
                    Toast.LENGTH_SHORT).show()
    }

}
