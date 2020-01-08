package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_member_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.setTextViewStartingWithBoldSpan
import org.systers.mentorship.viewmodels.MemberProfileViewModel
import org.systers.mentorship.vo.ErrorResource
import org.systers.mentorship.vo.LoadingResource
import org.systers.mentorship.vo.SuccessResource
import org.systers.mentorship.vo.UserVO

/**
 * This activity will show the public profile of a user of the system
 */
class MemberProfileActivity : BaseActivity() {

    private lateinit var memberProfileViewModel: MemberProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_profile)

        supportActionBar?.title = getString(R.string.member_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getIntExtra(Constants.MEMBER_USER_ID, 0)

        memberProfileViewModel = ViewModelProvider(this).get(MemberProfileViewModel::class.java)
        memberProfileViewModel.getUserProfile(userId).observe(this, Observer { resource ->
            when (resource) {
                is SuccessResource -> {
                    hideProgressBar()
                    setUserProfile(resource.data)
                }
                is LoadingResource -> showProgressBar()
                is ErrorResource -> {
                    hideProgressBar()
                    Snackbar.make(getRootView(), resource.message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })

        btnSendRequest.setOnClickListener {
            memberProfileViewModel.getUserProfile(userId).observe(this, Observer { resource ->
                when (resource) {
                    is SuccessResource -> {
                        val intent =
                            Intent(this@MemberProfileActivity, SendRequestActivity::class.java)
                        intent.putExtra(
                            SendRequestActivity.OTHER_USER_ID_INTENT_EXTRA,
                            resource.data.id
                        )
                        intent.putExtra(
                            SendRequestActivity.OTHER_USER_NAME_INTENT_EXTRA,
                            resource.data.name
                        )
                        startActivity(intent)
                    }
                    is LoadingResource -> showProgressBar()
                    is ErrorResource -> {
                        hideProgressBar()
                        Snackbar.make(getRootView(), resource.message, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            })
        }
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

    private fun setUserProfile(user: UserVO) {
        tvName.text = user.name

        if (user.isAvailableToMentor != null) {
            setTextViewStartingWithBoldSpan(
                tvAvailableToMentor,
                getString(R.string.available_to_mentor),
                if (user.isAvailableToMentor!!)
                    getString(R.string.yes) else getString(R.string.no)
            )
        }
        if (user.needsMentoring != null) {
            setTextViewStartingWithBoldSpan(
                tvNeedMentoring,
                getString(R.string.need_mentoring),
                if (user.needsMentoring!!)
                    getString(R.string.yes) else getString(R.string.no)
            )
        }
        setTextViewStartingWithBoldSpan(tvBio, getString(R.string.bio), user.bio)
        setTextViewStartingWithBoldSpan(
            tvLocation, getString(R.string.location), user.location
        )
        setTextViewStartingWithBoldSpan(
            tvOrganization, getString(R.string.organization), user.organization
        )
        setTextViewStartingWithBoldSpan(
            tvOccupation, getString(R.string.occupation), user.occupation
        )
        setTextViewStartingWithBoldSpan(
            tvInterests, getString(R.string.interests), user.interests
        )
        setTextViewStartingWithBoldSpan(
            tvSkills, getString(R.string.skills), user.skills
        )
        setTextViewStartingWithBoldSpan(
            tvUsername, getString(R.string.username), user.username
        )
        setTextViewStartingWithBoldSpan(
            tvSlackUsername, getString(R.string.slack_username), user.slackUsername
        )
    }
}
