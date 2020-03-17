package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.systers.mentorship.R

class ResetPasswordActivity : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        firebaseAuth = FirebaseAuth.getInstance()
        btnlogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnreset.setOnClickListener {
            val useremail: String = etemail.toString().trim({ it <= ' ' })

            if (useremail == "") {
                Toast.makeText(this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth!!.sendPasswordResetEmail(useremail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this, "Error in sending password reset email!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
