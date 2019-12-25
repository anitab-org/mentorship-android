package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        tvDeleteAccount.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.delete_account)
            builder.setMessage(R.string.delete_account_message)
            builder.setPositiveButton(R.string.delete) { _, _ ->
                //@todo any function here
                Toast.makeText(applicationContext,R.string.deleted_account_message, Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
