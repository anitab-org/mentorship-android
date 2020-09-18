package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_settings.*
import org.systers.mentorship.R
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.models.User
import org.systers.mentorship.repository.DbRepository
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment
import org.systers.mentorship.viewmodels.ProfileViewModel
import org.systers.mentorship.viewmodels.SettingsViewModel
import org.systers.mentorship.viewmodels.providers.ProfileViewModelProvider
import org.systers.mentorship.viewmodels.providers.SettingsViewModelProvider

class SettingsActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var profileViewModel: ProfileViewModel

    private val repo = DbRepository(AppDatabase(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsViewModel = ViewModelProvider(this, SettingsViewModelProvider(application, repo)).get(SettingsViewModel::class.java)
        profileViewModel = ViewModelProvider(this, ProfileViewModelProvider(application, repo)).get(ProfileViewModel::class.java)
        settingsViewModel.successfulDelete.observeForever {
            when {
                it -> {
                    Toast.makeText(this, R.string.account_deleted, Toast.LENGTH_SHORT).show()
                    preferenceManager.clear()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAffinity()
                }
                else -> Toast.makeText(this, settingsViewModel.message, Toast.LENGTH_SHORT).show()
            }
        }

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_logout)
            builder.setMessage(R.string.confirm_logout_msg)
            builder.setPositiveButton(R.string.logout) { _, _ ->
                preferenceManager.clear()
                profileViewModel.getProfileDetails().observe(this, Observer {
                    if(it != null && it.isNotEmpty()){
                        settingsViewModel.deleteProfile(it[0])
                    }
                })
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

        tvDeleteAccount.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.delete_account))
            builder.setMessage(R.string.delete_account_msg)
            builder.setPositiveButton(getString(R.string.delete)) { _, _ ->
                settingsViewModel.deleteUser()
            }
            builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            builder.create().show()
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
