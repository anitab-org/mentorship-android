package org.systers.mentorship.view.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_feedback.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ActivityFeedbackBinding
import org.systers.mentorship.viewmodels.ProfileViewModel

class FeedbackActivity : BaseActivity(), View.OnClickListener {


    private var feedbackRating : Int = 0
    private var feedbackEmailErr : Boolean = true
    private var ratingBtnList : ArrayList<ImageButton>? = null
    private var feedbackMsgErr : Boolean = true
    private var feedbackCategory : String = ""
    private lateinit var feedbackBinding: ActivityFeedbackBinding
    private val profileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedbackBinding = DataBindingUtil.setContentView(this, R.layout.activity_feedback)

        supportActionBar?.title = getString(R.string.feedback)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        //init email ID  field
        srlProfile1.setOnRefreshListener { fetchNewest() }
        profileViewModel.successfulGet.observe(this, Observer { successful ->
            srlProfile1.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    feedbackBinding.user = profileViewModel.user
                    Log.i("Here is the mail", feedbackBinding.user?.email?.trim())
                    feedbackEmailErr = false
                    FeedbackpageSendbtn.isEnabled = true
                } else {
                    Snackbar.make(findViewById(android.R.id.content), profileViewModel.message,
                            Snackbar.LENGTH_LONG).show()
                }
            }
        })
        fetchNewest()



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

    private fun fetchNewest() {
        srlProfile1.isRefreshing = true
        profileViewModel.getProfile()
    }

}
