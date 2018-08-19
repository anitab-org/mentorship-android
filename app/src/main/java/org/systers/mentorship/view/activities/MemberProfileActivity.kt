package org.systers.mentorship.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_member_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.remote.responses.UserResponse
import org.systers.mentorship.utils.setTextViewStartingWithBoldSpan
import org.systers.mentorship.view.fragments.MembersFragment
import org.systers.mentorship.viewmodels.MemberProfileViewModel

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

        val userId = intent.getIntExtra(MembersFragment.MEMBER_USER_ID_EXTRA, 0)

        memberProfileViewModel = ViewModelProviders.of(this).get(MemberProfileViewModel::class.java)
        memberProfileViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    setUserProfile(memberProfileViewModel.userProfile)
                } else {
                    Snackbar.make(getRootView(), memberProfileViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        showProgressDialog(getString(R.string.fetch_user_profile))
        memberProfileViewModel.getUserProfile(userId)
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

    private fun setUserProfile(userData: UserResponse) {
        tvName.text = userData.name
        setTextViewStartingWithBoldSpan(
                tvAvailableToMentor,
                getString(R.string.available_to_mentor),
                if (userData.isAvailableToMentor)
                    getString(R.string.yes) else getString(R.string.no))
        setTextViewStartingWithBoldSpan(
                tvNeedMentoring,
                getString(R.string.need_mentoring),
                if (userData.needsMentoring)
                    getString(R.string.yes) else getString(R.string.no))
        setTextViewStartingWithBoldSpan(tvBio, getString(R.string.bio), userData.bio)
        setTextViewStartingWithBoldSpan(
                tvLocation, getString(R.string.location), userData.location)
        setTextViewStartingWithBoldSpan(
                tvOrganization, getString(R.string.organization), userData.organization)
        setTextViewStartingWithBoldSpan(
                tvOccupation, getString(R.string.occupation), userData.occupation)
        setTextViewStartingWithBoldSpan(
                tvInterests, getString(R.string.interests), userData.interests)
        setTextViewStartingWithBoldSpan(
                tvSkills, getString(R.string.skills), userData.skills)
        setTextViewStartingWithBoldSpan(
                tvUsername, getString(R.string.username), userData.username)
        setTextViewStartingWithBoldSpan(
                tvSlackUsername, getString(R.string.slack_username), userData.slackUsername)
    }

    override fun onDestroy() {
        super.onDestroy()
        memberProfileViewModel.successful.removeObservers(this)
        memberProfileViewModel.successful.value = null
    }
}
