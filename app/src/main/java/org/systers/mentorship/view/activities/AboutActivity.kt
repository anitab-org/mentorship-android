package org.systers.mentorship.view.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var aboutBinding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)
        setTitle(R.string.fragment_title_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        aboutBinding.btnGit.setOnClickListener(this)
        aboutBinding.btnSlack.setOnClickListener(this)
        aboutBinding.btnWebsite.setOnClickListener(this)
        aboutBinding.btnTermsCondition.setOnClickListener(this)
        aboutBinding.btncodeofconduct.setOnClickListener(this)
        aboutBinding.btnprivacypolicy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val url = when (v?.id) {
            R.id.btnGit -> getString(R.string.url_github)
            R.id.btnSlack -> getString(R.string.url_zulip)
            R.id.btnWebsite -> getString(R.string.url_website)
            R.id.btnTermsCondition -> getString(R.string.url_terms)
            R.id.btncodeofconduct -> getString(R.string.url_code_of_conduct)
            R.id.btnprivacypolicy -> getString(R.string.url_privacy)

            else -> {
                return
            }
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this,R.string.activity_not_found_exception_error, Toast.LENGTH_LONG).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
