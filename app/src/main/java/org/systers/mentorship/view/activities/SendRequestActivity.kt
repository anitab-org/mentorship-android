package org.systers.mentorship.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import org.systers.mentorship.R
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_send_request.*
import org.systers.mentorship.remote.requests.SendRequest
import org.systers.mentorship.utils.SEND_REQUEST_END_DATE_FORMAT
import org.systers.mentorship.utils.convertDateIntoUnixTimestamp
import org.systers.mentorship.utils.getAuthTokenPayload
import org.systers.mentorship.viewmodels.SendRequestViewModel

/**
 * This activity will show a Mentorship request detail from the Requests List
 */
class SendRequestActivity: BaseActivity() {

    companion object {
        const val OTHER_USER_ID_INTENT_EXTRA = "OTHER_USER_ID_INTENT_EXTRA"
        const val OTHER_USER_NAME_INTENT_EXTRA = "OTHER_USER_NAME_INTENT_EXTRA"
    }

    private lateinit var sendRequestViewModel: SendRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_request)
        supportActionBar?.title = getString(R.string.send_request)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val otherUserName = intent.getStringExtra(OTHER_USER_NAME_INTENT_EXTRA)
        val otherUserId = intent.getIntExtra(OTHER_USER_ID_INTENT_EXTRA, 0)
        val currentUserId = getAuthTokenPayload().identity
        setObservables()
        populateView(otherUserName, otherUserId, currentUserId)
    }

    private fun populateView(userName: String, otherUserId: Int, currentUserId: Int) {

        tvOtherUserName.text = userName

        btnSendRequest.setOnClickListener {

            val mentorId: Int
            val menteeId: Int
            val notes = etRequestNotes.text.toString()
            val endDate = convertDateIntoUnixTimestamp(
                    tvRequestEndDate.text.toString(), SEND_REQUEST_END_DATE_FORMAT)

            when {
                rgCurrentUserRole.checkedRadioButtonId == btnMentorRadio.id -> {
                    mentorId = currentUserId
                    menteeId = otherUserId
                }
                rgCurrentUserRole.checkedRadioButtonId == btnMenteeRadio.id -> {
                    menteeId = currentUserId
                    mentorId = otherUserId
                }
                else -> {

                    Snackbar.make(getRootView(), "Please select your role for the mentorship relation", Snackbar.LENGTH_LONG)
                            .show()
                    return@setOnClickListener
                }
            }

            val sendRequestData = SendRequest(
                    menteeId = menteeId,
                    mentorId = mentorId,
                    notes = notes,
                    endDate = endDate
            )

            sendRequestViewModel.sendRequest(sendRequestData)
        }
    }

    private fun setObservables() {
        sendRequestViewModel  = ViewModelProviders.of(this).get(SendRequestViewModel::class.java)
        sendRequestViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, sendRequestViewModel.message, Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Snackbar.make(getRootView(), sendRequestViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}