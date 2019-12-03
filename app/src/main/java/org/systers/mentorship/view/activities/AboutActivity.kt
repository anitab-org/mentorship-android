package org.systers.mentorship.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle(R.string.fragment_title_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnGit.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_github))
            intent.putExtra(Constants.NAME, Constants.GITHUB)
            startActivity(intent)
        }
        btnSlack.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_slack))
            intent.putExtra(Constants.NAME, Constants.SLACK)
            startActivity(intent)
        }
        btnWebsite.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_website))
            intent.putExtra(Constants.NAME, Constants.WEBSITE)
            startActivity(intent)
        }
        btnTermsCondition.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_terms))
            intent.putExtra(Constants.NAME, Constants.TERMS)
            startActivity(intent)
        }
        btnprivacypolicy.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_privacy))
            intent.putExtra(Constants.NAME, Constants.PRIVACY_POLICY)
            startActivity(intent)
        }
        btncodeofconduct.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(Constants.URL, getString(R.string.url_code_of_conduct))
            intent.putExtra(Constants.NAME, Constants.CODE_OF_CONDUCT)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
