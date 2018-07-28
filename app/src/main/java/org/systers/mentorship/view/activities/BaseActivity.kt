@file:Suppress("DEPRECATION")

package org.systers.mentorship.view.activities

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.systers.mentorship.utils.ProgressBar

/**
 * An Activity class which other Activities can extend from. It provides some basic functions like
 * showing/hiding progress dialog bars, hiding keyboard etc.
 */
open class BaseActivity: AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null
    private var progressBar: ProgressBar? = null

    protected fun showProgressDialog(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, ProgressDialog.STYLE_SPINNER)
            progressDialog?.setCancelable(false)
        }
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    protected fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog?.dismiss()
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
