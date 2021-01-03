package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private var clearHistory = false

    private lateinit var aboutBinding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)
        setTitle(R.string.fragment_title_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        aboutBinding.webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (clearHistory) {
                    clearHistory = false
                    view.clearHistory()
                }
                super.onPageFinished(view, url)

                showWebView()
            }
        }

        hideWebView()

        aboutBinding.apply {
            btnGit.setOnClickListener(this@AboutActivity)
            btnSlack.setOnClickListener(this@AboutActivity)
            btnWebsite.setOnClickListener(this@AboutActivity)
            btnTermsCondition.setOnClickListener(this@AboutActivity)
            btncodeofconduct.setOnClickListener(this@AboutActivity)
            btnprivacypolicy.setOnClickListener(this@AboutActivity)
        }
    }

    override fun onClick(v: View?) {
        val url = when(v?.id) {
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

        showProgress()
        aboutBinding.webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && aboutBinding.webView.canGoBack()) {
            aboutBinding.webView.goBack()
            return true
        } else if(aboutBinding.webView.visibility == View.VISIBLE) {
            hideWebView()
            return true
        }

        // exit activity
        return super.onKeyDown(keyCode, event)
    }

    private fun showWebView() {
        aboutBinding.apply {
            scrollView.visibility = View.GONE
            webView.visibility = View.VISIBLE
        }
        hideProgress()
    }

    private fun hideWebView() {
        clearHistory = true
        aboutBinding.apply {
            scrollView.visibility = View.VISIBLE
            webView.visibility = View.GONE
        }
        hideProgress()
    }

    private fun showProgress() {
        aboutBinding.apply {
            progressBar.visibility = View.VISIBLE
            webView.visibility = View.GONE
            scrollView.visibility = View.GONE
        }
    }

    private fun hideProgress() {
        aboutBinding.progressBar.visibility = View.GONE
    }
}
