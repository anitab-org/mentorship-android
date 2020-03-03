package org.systers.mentorship.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_about.*
import org.systers.mentorship.R


class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private var clearHistory = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle(R.string.fragment_title_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView.webViewClient = object: WebViewClient() {
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

        btnGit.setOnClickListener(this)
        btnSlack.setOnClickListener(this)
        btnWebsite.setOnClickListener(this)
        btnTermsCondition.setOnClickListener(this)
        btncodeofconduct.setOnClickListener(this)
        btnprivacypolicy.setOnClickListener(this)
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
        webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        } else if(webView.visibility == View.VISIBLE) {
            hideWebView()
            return true
        }

        // exit activity
        return super.onKeyDown(keyCode, event)
    }

    private fun showWebView() {
        scrollView.visibility = View.GONE
        webView.visibility = View.VISIBLE
        hideProgress()
    }

    private fun hideWebView() {
        clearHistory = true
        scrollView.visibility = View.VISIBLE
        webView.visibility = View.GONE
        hideProgress()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
        webView.visibility = View.GONE
        scrollView.visibility = View.GONE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}
