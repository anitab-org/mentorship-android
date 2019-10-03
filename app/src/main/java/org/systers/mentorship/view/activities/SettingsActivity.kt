package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_settings.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.ChangePasswordFragment
import org.systers.mentorship.viewmodels.DeleteUserViewModel

class SettingsActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var deleteUserViewModel: DeleteUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        deleteUserViewModel = ViewModelProviders.of(this).get(DeleteUserViewModel::class.java)
        deleteUserViewModel.successfulDelete.observe(this, Observer { successful ->

            if (successful != null) {
                when {
                    successful -> {
                        Toast.makeText(this, getString(R.string.user_deleted), Toast.LENGTH_SHORT).show()
                        logout()
                    }
                    else -> Toast.makeText(this, deleteUserViewModel.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


        tvLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_logout)
            builder.setMessage(R.string.confirm_logout_msg)
            builder.setPositiveButton(R.string.logout) { _, _ ->
                logout()
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

        tvDeleteAccount.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.confirm_delete)
            builder.setMessage(R.string.confirm_delete_msg)
            builder.setPositiveButton(R.string.delete) { _, _ ->
                deleteUserViewModel.deleteUser()
            }
            builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

    fun logout(){
        preferenceManager.clear()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
