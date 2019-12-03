package org.systers.mentorship.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants

class WebViewActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView
    private lateinit var webAddress: String
    private lateinit var title: String
    private lateinit var progressBar: ProgressBar
    private lateinit var progressFrame: FrameLayout
    lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webAddress = intent.getStringExtra(Constants.URL)
        title = intent.getStringExtra(Constants.NAME)

        mWebView = findViewById(R.id.webView)
        mWebView.webViewClient = WebViewClient()
        mWebView.loadUrl(webAddress)
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        swipe = findViewById(R.id.swipe)
        swipe.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        swipe.setOnRefreshListener { loadWebPage() }
        loadWebPage()
        swipe.isRefreshing = false
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadWebPage() {
        progressFrame = findViewById(R.id.progress_frame)
        progressBar = findViewById(R.id.progress)
        progressBar.max = 100
        mWebView = findViewById(R.id.webView)
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                progressFrame.visibility = View.VISIBLE
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                progressFrame.visibility = View.VISIBLE
                progressBar.progress = progress
                supportActionBar?.title = "Loading"
                if (progress == 100) {
                    progressFrame.visibility = View.GONE
                    supportActionBar?.title = title
                    swipe.isRefreshing = false
                }
                super.onProgressChanged(view, progress)
            }
        }
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.setAppCacheEnabled(true)
        mWebView.isVerticalScrollBarEnabled = false
        mWebView.loadUrl(webAddress)
        mWebView.isHorizontalScrollBarEnabled = false
        progressBar.progress = 0
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}

