package org.systers.mentorship.view.activities

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import org.systers.mentorship.R
import android.util.Patterns
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : BaseActivity(), View.OnClickListener {


    private var feedbackRating : Int = 0
    private var feedbackEmailErr : Boolean = true
    private var ratingBtnList : ArrayList<ImageButton>? = null
    private var feedbackMsgErr : Boolean = true
    private var feedbackCategory : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        supportActionBar?.title = getString(R.string.feedback)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        //init email ID input field
        FeedbackpageEmail.isErrorEnabled = true
        FeedbackpageEmail.error = getString(R.string.email_error)
        FeedbackpageEmail.editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!FeedbackpageEmail.editText?.text.isNullOrEmpty()&&!isValidEmail(s!!))
                {
                    FeedbackpageEmail.isErrorEnabled = true
                    FeedbackpageEmail.error = getString(R.string.valid_error)
                    feedbackEmailErr = true
                }
                else
                {   if (s.toString().isEmpty())
                {
                    FeedbackpageEmail.isErrorEnabled = true
                    FeedbackpageEmail.error = getString(R.string.email_error)
                    feedbackEmailErr = true
                }
                else
                {
                    FeedbackpageEmail.isErrorEnabled = false
                    feedbackEmailErr = false
                }
                }
            }

        })

        //init message input field
        FeedbackPageMessage.isErrorEnabled = true
        FeedbackPageMessage.error = getString(R.string.msg_error)
        FeedbackPageMessage.editText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length > FeedbackPageMessage.counterMaxLength)
                {
                    FeedbackPageMessage.isErrorEnabled = true
                    FeedbackPageMessage.error = getString(R.string.char_error)
                    feedbackMsgErr = true
                }
                else
                {
                    if (s.toString().isEmpty())
                    {
                        FeedbackPageMessage.isErrorEnabled = true
                        FeedbackPageMessage.error = getString(R.string.msg_error)
                        feedbackMsgErr = true
                    }
                    else
                    {
                        FeedbackPageMessage.isErrorEnabled = false
                        feedbackMsgErr = false
                    }
                }
            }
        })

        //init ratingBtnList, an arraylist of ImageButtons, it is going to be used to add rating and change image resource, see fun assignRating()
        ratingBtnList = ArrayList()
        ratingBtnList?.add(FeedbackpageS1)
        ratingBtnList?.add(FeedbackpageS2)
        ratingBtnList?.add(FeedbackpageS3)
        ratingBtnList?.add(FeedbackpageS4)
        ratingBtnList?.add(FeedbackpageS5)
        //set the onclick listener for each of the star buttons
        for(i in ratingBtnList!!)
        {
            i.setOnClickListener(this)
        }

        //set click listeners for category radio buttons
        FeedbackpageRd1.setOnClickListener(this)
        FeedbackpageRd2.setOnClickListener(this)
        FeedbackpageRd3.setOnClickListener(this)
        //init category = "bug"
        feedbackCategory = getString(R.string.bug)

        //Final submit button
        FeedbackpageSendbtn.setOnClickListener {
            if (!feedbackEmailErr && !feedbackMsgErr && feedbackRating != 0) {
                Toast.makeText(this, getString(R.string.feedback_thank), Toast.LENGTH_SHORT).show()
                //add backend code for adding rating, category, message, email ID
            }
            else
            {
                if (feedbackMsgErr || feedbackEmailErr || feedbackRating == 0) {
                    Toast.makeText(this, getString(R.string.input), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    //fun used to validate email ID
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Alert")
        //set message for alert dialog
        builder.setMessage("Are you sure you want to Discard?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            super.onBackPressed()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.FeedbackpageS1 -> {
                assignRating(1)
            }
            R.id.FeedbackpageS2 -> {
                assignRating(2)
            }
            R.id.FeedbackpageS3 -> {

                assignRating(3)
            }
            R.id.FeedbackpageS4 -> {
                assignRating(4)
            }
            R.id.FeedbackpageS5 -> {
                assignRating(5)
            }
            R.id.FeedbackpageRd1 -> {
                if (FeedbackpageRd1.isChecked)
                {
                    feedbackCategory = FeedbackpageRd1.text.toString()
                }

            }
            R.id.FeedbackpageRd2 -> {
                if (FeedbackpageRd2.isChecked)
                {
                    feedbackCategory = FeedbackpageRd2.text.toString()
                }
            }
            R.id.FeedbackpageRd3 -> {
                if (FeedbackpageRd3.isChecked)
                {
                    feedbackCategory = FeedbackpageRd3.text.toString()
                }
            }
        }

    }

    //fun used to assign rating and change image resource of star image buttons
    fun assignRating(starIndex : Int) {
        if (feedbackRating != starIndex) {

            for (i in 0 until feedbackRating)
            {
                ratingBtnList?.get(i)?.setImageResource(R.drawable.ic_star_border_black_24dp)
            }
            for (i in 0 until starIndex) {
                ratingBtnList?.get(i)?.setImageResource(R.drawable.ic_star_black_24dp)
            }
            feedbackRating = starIndex
        }
    }

}
