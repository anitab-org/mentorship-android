package org.systers.mentorship.view.activities

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
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
import android.view.Window
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_confirm_request.*
import org.systers.mentorship.view.fragments.RequestPagerFragment

/**
 * This activity will show a Mentorship request detail from the Requests List
 */
class RequestDetailActivity: BaseActivity() {

    private lateinit var requestDetailViewModel: RequestDetailViewModel

    private val mentorshipRelationResponse by lazy {
        intent.getParcelableExtra<Relationship>(Constants.RELATIONSHIP_EXTRA)
    }

    private var otherUserName:String=""
    private var isFromMentee:Boolean=false

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
        isFromMentee = relationResponse.actionUserId == relationResponse.mentee.id

        val requestDirection = getString(if (relationResponse.sentByMe) R.string.to else R.string.from)

        otherUserName = if (relationResponse.sentByMe) {
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

        /**
         * The onClickListeners() passes the control to generateDialog() method
         * which generates the dialog box according to the params provided.
         * */

        btnDelete.setOnClickListener {
            requestDetailViewModel.deleteRequest(relationResponse.id)
        }

        btnReject.setOnClickListener {
            generateDialog(resources.getString(R.string.reject_lower_case),relationResponse)
        }

        btnAccept.setOnClickListener {
            generateDialog(resources.getString(R.string.accept_lower_case),relationResponse)
        }

    }

    /**
     * This method generates the dialog box for different types of request according to the params,
     * asking for the confirmation of user choice.
     * */

    private fun generateDialog(typeRequest:String,relationResponse: Relationship){

        var role=if(isFromMentee)resources.getString(R.string.mentee_lower_case) else resources.getString(R.string.mentor_lower_case)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_confirm_request)

        /**
         * Customising the text to be displayed on DialogBox
         * In this, three values are variables
         *      1. typeRequest --> which takes value of 'accept/reject/delete'.
         *      2. otherUserName --> the username of the person who has sent this request
         *      3. role --> the role of the person, whether a 'mentor/mentee'
         * */
        var message=resources.getString(R.string.confirm_dialog_message,typeRequest,otherUserName,role)
        dialog.findViewById<TextView>(R.id.tvTitleDialog)
                .setText(message)

        dialog.btnYes.setOnClickListener {

            if (typeRequest.equals(R.string.accept_lower_case)){
                requestDetailViewModel.acceptRequest(relationResponse.id)
            }
            else if (typeRequest.equals(R.string.reject_lower_case)){
                requestDetailViewModel.rejectRequest(relationResponse.id)
            }
        }

        dialog.btnNo.setOnClickListener {
            dialog.hide()
        }

        dialog.show()
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
