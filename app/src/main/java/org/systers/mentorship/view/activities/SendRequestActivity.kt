package org.systers.mentorship.view.activities

import android.app.DatePickerDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_send_request.*
import org.systers.mentorship.R
import org.systers.mentorship.models.RelationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.utils.*
import org.systers.mentorship.viewmodels.RequestsViewModel
import org.systers.mentorship.viewmodels.SendRequestViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * This activity will show a Mentorship request detail from the Requests List
 */
class SendRequestActivity: BaseActivity() {

    private lateinit var myCalendar : Calendar
    companion object {
        const val OTHER_USER_ID_INTENT_EXTRA = "OTHER_USER_ID_INTENT_EXTRA"
        const val OTHER_USER_NAME_INTENT_EXTRA = "OTHER_USER_NAME_INTENT_EXTRA"
    }

    private lateinit var sendRequestViewModel: SendRequestViewModel
    private var sendRequest: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_request)

        tvRequestEndDate.isEnabled = false
        supportActionBar?.title = getString(R.string.send_request)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val otherUserName = intent.getStringExtra(OTHER_USER_NAME_INTENT_EXTRA)
        val otherUserId = intent.getIntExtra(OTHER_USER_ID_INTENT_EXTRA, 0)
        val currentUserId = getAuthTokenPayload().identity
        setObservables()
        populateView(otherUserName, otherUserId, currentUserId)

        //Setting default date , 1 month after a current date
        myCalendar = Calendar.getInstance()
        myCalendar.add(Calendar.MONTH , 1)
        updateEndDateEditText()

        var date : DatePickerDialog.OnDateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                myCalendar.set(Calendar.YEAR , year)
                myCalendar.set(Calendar.MONTH  , month)
                myCalendar.set(Calendar.DAY_OF_MONTH  , dayOfMonth)
                updateEndDateEditText()
            }
        }
        ivCalendar.setOnClickListener {
          DatePickerDialog(this , date ,
                  myCalendar.get(Calendar.YEAR) ,
                  myCalendar.get(Calendar.MONTH) ,
                  myCalendar.get(Calendar.DAY_OF_MONTH)).show()
      }
    }
    private fun updateEndDateEditText() {
        var sdf : SimpleDateFormat = SimpleDateFormat(SEND_REQUEST_END_DATE_FORMAT , Locale.US)
        var editable = SpannableStringBuilder(sdf.format(myCalendar.time))
        tvRequestEndDate.text =  editable
    }

    private fun populateView(userName: String, otherUserId: Int, currentUserId: Int) {
        tvOtherUserName.text = userName
        btnSendRequest.setOnClickListener {
            sendRequest = true //reset to true every time clicked
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

            if(!TextUtils.isEmpty(notes)) {

                val sendRequestData = RelationshipRequest(
                        menteeId = menteeId,
                        mentorId = mentorId,
                        notes = notes,
                        endDate = endDate
                )
                /*
                 make API call, add observer and see if similar field exists.
                 conditions for failure: receiver, mentor/mentee field, end date are same
                 */
                lateinit var requestsList: List<Relationship>
                val requestsViewModel = ViewModelProviders.of(this).get(RequestsViewModel::class.java)
                requestsViewModel.successful.observe(this, Observer {successful ->
                    if (successful != null) {
                            if (successful) {
                                requestsList = requestsViewModel.allRequestsList
                                /*
                                 timestamp directly not compared: app converts end_date to
                                 float leading to loss of precision. So it's converted to date
                                 and compared
                                 */
                                requestsList = requestsList.filter {
                                    val isPendingState = RelationState.PENDING.value == it.state
                                    val hasEndTimePassed = getUnixTimestampInMilliseconds(it.endsOn) < System.currentTimeMillis()
                                    val receivedDate = convertUnixTimestampIntoStr(
                                            it.endsOn, SEND_REQUEST_END_DATE_FORMAT
                                    )
                                    val userDate = convertUnixTimestampIntoStr(
                                            endDate.toFloat(), SEND_REQUEST_END_DATE_FORMAT
                                    )
                                    isPendingState && !hasEndTimePassed &&
                                            userDate == receivedDate &&
                                            findReceiver(it) == otherUserId &&
                                            it.mentee.id == sendRequestData.menteeId &&
                                            it.mentor.id == sendRequestData.mentorId
                                }
                                if(requestsList.isEmpty()){
                                    sendRequestViewModel.sendRequest(sendRequestData)
                                } else {
                                    Snackbar.make(
                                            getRootView(), getString(R.string.similar_request_error),
                                            Snackbar.LENGTH_LONG
                                    ).show()
                                }
                            }
                            else
                            {
                                Snackbar.make(
                                        getRootView(),
                                        requestsViewModel.message,
                                        Snackbar.LENGTH_LONG
                                ).show()
                            }
                    }

                })
                requestsViewModel.getAllMentorshipRelations()

            } else {

                etRequestNotes.error = getString(R.string.notes_empty_error)
            }
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

    private fun findReceiver(relationship: Relationship): Int{
        // helper function to find receiver and block duplicate requests
        if(relationship.actionUserId != relationship.mentee.id){
            return relationship.mentee.id
        }
        return relationship.mentor.id
    }
}
