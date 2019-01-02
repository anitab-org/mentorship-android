package org.systers.mentorship.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_request_detail.*
import org.systers.mentorship.R
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.*
import org.systers.mentorship.viewmodels.RequestDetailViewModel
import android.content.Intent
import org.systers.mentorship.view.fragments.RequestPagerFragment

/**
 * This activity will show a Mentorship request detail from the Requests List
 */
class RequestDetailActivity: BaseActivity() {

    private lateinit var requestDetailViewModel: RequestDetailViewModel

    private val mentorshipRelationResponse by lazy {
        intent.getParcelableExtra<Relationship>(Constants.RELATIONSHIP_EXTRA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_detail)
        supportActionBar?.title = getString(R.string.request_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        populateView(mentorshipRelationResponse)
        setObservables(mentorshipRelationResponse)
        setOnClickListeners(mentorshipRelationResponse)
    }

    private fun populateView(relationResponse: Relationship) {
        tvRequestNotes.text = relationResponse.notes
        val isFromMentee: Boolean = relationResponse.actionUserId == relationResponse.mentee.id

        val requestDirection = getString(if (relationResponse.sentByMe) R.string.to else R.string.from)

        val otherUserName = if (relationResponse.sentByMe) {
            if (isFromMentee) {
                relationResponse.mentor.name
            } else {
                relationResponse.mentee.name
            }
        } else {
            if (isFromMentee) {
                relationResponse.mentee.name
            } else {
                relationResponse.mentor.name
            }
        }
        tvOtherUserName.text = getString(R.string.request_direction_formatted, requestDirection, otherUserName)

        val summaryStrId = if (relationResponse.sentByMe) {
            R.string.request_sent_by_current_user_message
        } else {
            R.string.request_sent_by_other_user_message
        }
        val actionUserRole = getString(if (isFromMentee) R.string.mentee else R.string.mentor)
        val requestEndDate = convertUnixTimestampIntoStr(
                relationResponse.endsOn, EXTENDED_DATE_FORMAT)

        val requestSummaryMessage = getString(summaryStrId,
                otherUserName, actionUserRole, requestEndDate)
        tvRequestSummary.text = requestSummaryMessage

        if (relationResponse.state == RelationState.PENDING.value) {
            setActionButtons(relationResponse)
        } else {
            setStateMessage(relationResponse)
        }

        // TODD: Needed to enable scrolling on text view
        tvRequestNotes.movementMethod = ScrollingMovementMethod()
    }

    private fun setActionButtons(relationResponse: Relationship) {
        val hasEndTimePassed = getUnixTimestampInMilliseconds(relationResponse.endsOn) < System.currentTimeMillis()
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

    private fun setStateMessage(relationResponse: Relationship) {
        val stateStrId = when (relationResponse.state) {
            RelationState.ACCEPTED.value -> R.string.accepted
            RelationState.REJECTED.value -> R.string.rejected
            RelationState.CANCELLED.value -> R.string.cancelled
            RelationState.COMPLETED.value -> R.string.completed
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

    private fun setOnClickListeners(relationResponse: Relationship) {

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

    private fun setObservables(relationResponse: Relationship) {
        requestDetailViewModel  = ViewModelProviders.of(this).get(RequestDetailViewModel::class.java)
        requestDetailViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    Toast.makeText(this, requestDetailViewModel.message, Toast.LENGTH_LONG).show()
                    val previousScreen = Intent(applicationContext, RequestPagerFragment::class.java)
                    previousScreen.putExtra(Constants.REQUEST_ID, relationResponse.id.toString())
                    setResult(Constants.DELETE_REQUEST_RESULT_ID, previousScreen)
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