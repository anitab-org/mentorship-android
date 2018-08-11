package org.systers.mentorship.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_request_detail.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.MentorshipRelationState
import org.systers.mentorship.remote.responses.MentorshipRelationResponse
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import org.systers.mentorship.utils.*
import org.systers.mentorship.viewmodels.RequestDetailViewModel

/**
 * This activity will show a Mentorship request detail from the Requests List
 */
class RequestDetailActivity: BaseActivity() {

    companion object {
        const val RELATION_INTENT_EXTRA = "RELATION_INTENT_EXTRA"
    }

    private lateinit var requestDetailViewModel: RequestDetailViewModel

    private val mentorshipRelationResponse by lazy {
        intent.getParcelableExtra<MentorshipRelationResponse>(RELATION_INTENT_EXTRA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_detail)
        supportActionBar?.title = getString(R.string.request_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        populateView(mentorshipRelationResponse)
        setObservables()
        setOnClickListeners(mentorshipRelationResponse)
    }

    private fun populateView(relationResponse: MentorshipRelationResponse) {
        tvRequestNotes.text = relationResponse.notes
        val isFromMentee: Boolean = relationResponse.actionUserId == relationResponse.mentee.id

        val requestDirection = getString(if (relationResponse.sentByMe) R.string.to else R.string.from)

        val otherUserName = if (relationResponse.sentByMe && isFromMentee) {
            relationResponse.mentor.name
        } else {
            relationResponse.mentee.name
        }
        tvOtherUserName.text = getString(R.string.request_direction_formatted, requestDirection, otherUserName)

        val summaryStrId = if (relationResponse.sentByMe) {
            R.string.request_sent_by_current_user_message
        } else {
            R.string.request_sent_by_other_user_message
        }
        val actionUserRole = getString(if (isFromMentee) R.string.mentee else R.string.mentor)
        val requestEndDate = convertUnixTimestampIntoStr(
                relationResponse.endAtTimestamp, EXTENDED_DATE_FORMAT)

        val requestSummaryMessage = getString(summaryStrId,
                otherUserName, actionUserRole, requestEndDate)
        tvRequestSummary.text = requestSummaryMessage

        if (relationResponse.state == MentorshipRelationState.PENDING.value) {
            setActionButtons(relationResponse)
        } else {
            setStateMessage(relationResponse)
        }

        // Needed to enable scrolling on text view
        tvRequestNotes.movementMethod = ScrollingMovementMethod()
    }

    private fun setActionButtons(relationResponse: MentorshipRelationResponse) {
        val hasEndTimePassed = getUnixTimestampInMilliseconds(relationResponse.endAtTimestamp) < System.currentTimeMillis()
        if (!hasEndTimePassed) {
            if (relationResponse.sentByMe) {
                btnDelete.visibility = View.VISIBLE
                btnAccept.visibility = View.GONE
                btnReject.visibility = View.GONE
            } else {
                btnDelete.visibility = View.GONE
                btnAccept.visibility = View.VISIBLE
                btnReject.visibility = View.VISIBLE
            }
            tvStateMessage.visibility = View.GONE
        } else {
            tvStateMessage.visibility = View.VISIBLE
            tvStateMessage.text = getString(R.string.relation_end_date_has_passed)
        }
    }

    private fun setStateMessage(relationResponse: MentorshipRelationResponse) {
        val stateStrId = when (relationResponse.state) {
            MentorshipRelationState.ACCEPTED.value -> R.string.accepted
            MentorshipRelationState.REJECTED.value -> R.string.rejected
            MentorshipRelationState.CANCELLED.value -> R.string.cancelled
            MentorshipRelationState.COMPLETED.value -> R.string.completed
            else -> {
                null
            }
        }

        stateStrId?.let {
            tvStateMessage.visibility = View.VISIBLE
            val stateStr = getString(stateStrId)
            val relationStateMessage = getString(R.string.relation_state_message, stateStr)
            tvStateMessage.text = getTextWithBoldWord(relationStateMessage, stateStr)
        }
    }

    private fun setOnClickListeners(relationResponse: MentorshipRelationResponse) {

        btnDelete.setOnClickListener {
            requestDetailViewModel.deleteRequest(relationResponse.id)
        }

        btnReject.setOnClickListener {
            requestDetailViewModel.rejectRequest(relationResponse.id)
        }

        btnAccept.setOnClickListener {
            requestDetailViewModel.acceptRequest(relationResponse.id)
        }
    }

    private fun setObservables() {
        requestDetailViewModel  = ViewModelProviders.of(this).get(RequestDetailViewModel::class.java)
        requestDetailViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, requestDetailViewModel.message, Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Snackbar.make(getRootView(), requestDetailViewModel.message, Snackbar.LENGTH_LONG)
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