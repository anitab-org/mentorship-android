package org.systers.mentorship.utils

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout

/**
 * Created by murad on 7/28/18.
 */
class ProgressBar(context: Context) {

    private val progressBar: ProgressBar

    init {

        val layout = (context as Activity).findViewById<View>(R.id.content).rootView as ViewGroup

        progressBar = ProgressBar(context, null, R.attr.progressBarStyleLargeInverse)
        progressBar.isIndeterminate = true

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)

        val rl = RelativeLayout(context)

        rl.gravity = Gravity.CENTER
        rl.addView(progressBar)

        layout.addView(rl, params)

        hide()
    }

    fun show() {
        progressBar.visibility = View.VISIBLE
    }

    fun hide() {
        progressBar.visibility = View.INVISIBLE
    }
}