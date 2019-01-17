package org.systers.mentorship.view.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about.*
import org.systers.mentorship.BuildConfig
import org.systers.mentorship.R
import android.content.Intent
import android.net.Uri

/**
 * This activity displays information about this App
 */
class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.title = getString(R.string.about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val res = resources
        val version = String.format(res.getString(R.string.version_string), BuildConfig.VERSION_CODE)
        tvAppVersion.text = version
        cvGithub.setOnClickListener {
            openWebPage(resources.getString(R.string.url_github))
        }
        cvSlack.setOnClickListener{
            openWebPage(resources.getString(R.string.url_slack))
        }
        cvWeb.setOnClickListener {
            openWebPage(resources.getString(R.string.url_website))
        }
        cvTerms.setOnClickListener {
            openWebPage(resources.getString(R.string.url_terms))
        }
        cvPrivacy.setOnClickListener {
            openWebPage(resources.getString(R.string.url_privacy))
        }
        cvCodeOfConduct.setOnClickListener {
            openWebPage(resources.getString(R.string.url_code_of_conduct))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * This function uses the implicit intent to open web pages.
     * @Param url: URL of the page
     */
    fun openWebPage(url: String) {

        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }
}
