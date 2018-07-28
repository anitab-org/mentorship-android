@file:Suppress("DEPRECATION")

package org.systers.mentorship.view.base

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.systers.mentorship.utils.ProgressBar

/**
 * An Activity class which other Activities can extend from. It provides some basic functions like
 * showing/hiding progress bars, hiding keyboard etc.
 */
open class BaseActivity: AppCompatActivity() {

    private var progress: ProgressDialog? = null
    private var progressBar: ProgressBar? = null

    protected fun showProgressDialog(message: String) {
        if (progress == null) {
            progress = ProgressDialog(this, ProgressDialog.STYLE_SPINNER)
            progress?.setCancelable(false)
        }
        progress?.setMessage(message)
        progress?.show()
    }

    protected fun hideProgressDialog() {
        if (progress != null && progress!!.isShowing)
            progress?.dismiss()
    }

    protected fun showProgressBar() {
        progressBar = ProgressBar(this)
        progressBar?.show()
    }

    protected fun hideProgressBar() {
        progressBar?.hide()
    }

    protected fun hideKeyboard(view: View) {
        val inputManager = this
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager
                .RESULT_UNCHANGED_SHOWN)
    }
}
