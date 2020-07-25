package org.systers.mentorship.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_member_profile.*
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.setTextViewStartingWithBoldSpan
import org.systers.mentorship.viewmodels.MemberProfileViewModel
import org.systers.mentorship.viewmodels.ProfileViewModel

/**
 * This activity will show the public profile of a user of the system
 */
class MemberProfileActivity : BaseActivity() {

    private val memberProfileViewModel by lazy {
        ViewModelProviders.of(this).get(MemberProfileViewModel::class.java)
    }
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var userProfile: User
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_profile)

        supportActionBar?.title = getString(R.string.member_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileViewModel= ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.successfulGet.observe(this, Observer {
            successful ->
            if (successful != null) {
                if (successful) {
                    setCurrentUser(profileViewModel.user)
                    if (!currentUser.availableToMentor!! && !currentUser.needMentoring!!) {
                        btnSendRequest.setBackgroundColor(Color.GRAY)
                    }
                } else {
                    Snackbar.make(getRootView(), profileViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })
        profileViewModel.getProfile()


        srlMemberProfile.setOnRefreshListener { fetchNewest() }

        memberProfileViewModel.successful.observe(this, Observer {
            successful ->
            srlMemberProfile.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    setUserProfile(memberProfileViewModel.userProfile)
                    if (!userProfile.availableToMentor!! && !userProfile.needMentoring!!) {
                        btnSendRequest.setBackgroundColor(Color.GRAY)
                    }
                } else {
                    Snackbar.make(getRootView(), memberProfileViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })

        val memberId = intent.getIntExtra(Constants.MEMBER_USER_ID, -1)

        memberProfileViewModel.userId = memberId

        fetchNewest()

        btnSendRequest.setOnClickListener {

            if (this::currentUser.isInitialized && this::userProfile.isInitialized) {

                if (currentUser.availableToMentor!! || currentUser.needMentoring!!) {

                    if (userProfile.availableToMentor!! || userProfile.availableToMentor!!) {
                        val intent = Intent(this@MemberProfileActivity, SendRequestActivity::class.java)
                        intent.putExtra(SendRequestActivity.OTHER_USER_ID_INTENT_EXTRA, userProfile.id)
                        intent.putExtra(SendRequestActivity.OTHER_USER_NAME_INTENT_EXTRA, userProfile.name)
                        startActivity(intent)

                    } else Snackbar.make(getRootView(),
                            getString(R.string.user_not_available_to_mentor_or_mentee),
                            Snackbar.LENGTH_LONG).show()

                } else Snackbar.make(getRootView(),
                        getString(R.string.you_not_available_to_mentor_or_mentee),
                        Snackbar.LENGTH_LONG).show()

            } else Snackbar.make(getRootView(),
                    getString(R.string.error_something_went_wrong),
                    Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_refresh -> {
                fetchNewest()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    private fun fetchNewest() {
        srlMemberProfile.isRefreshing = true
        memberProfileViewModel.getUserProfile()
    }

    private fun setCurrentUser(user: User) {
        currentUser = user
    }
    private fun setUserProfile(user: User) {
        userProfile = user
        tvName.text = user.name

        if (user.availableToMentor != null) {
            setTextViewStartingWithBoldSpan(
                    tvAvailableToMentor,
                    getString(R.string.available_to_mentor),
                    if (user.availableToMentor!!)
                        getString(R.string.yes) else getString(R.string.no))
        }
        if (user.needMentoring != null) {
            setTextViewStartingWithBoldSpan(
                    tvNeedMentoring,
                    getString(R.string.need_mentoring),
                    if (user.needMentoring!!)
                        getString(R.string.yes) else getString(R.string.no))
        }
        setTextViewStartingWithBoldSpan(tvBio, getString(R.string.bio), user.bio)
        setTextViewStartingWithBoldSpan(
                tvLocation, getString(R.string.location), user.location)
        setTextViewStartingWithBoldSpan(
                tvOrganization, getString(R.string.organization), user.organization)
        setTextViewStartingWithBoldSpan(
                tvOccupation, getString(R.string.occupation), user.occupation)
        setTextViewStartingWithBoldSpan(
                tvInterests, getString(R.string.interests), user.interests)
        setTextViewStartingWithBoldSpan(
                tvSkills, getString(R.string.skills), user.skills)
        setTextViewStartingWithBoldSpan(
                tvUsername, getString(R.string.username), user.username)
        setTextViewStartingWithBoldSpan(
                tvSlackUsername, getString(R.string.slack_username), user.slackUsername)
    }

    override fun onDestroy() {
        super.onDestroy()
        memberProfileViewModel.successful.removeObservers(this)
        memberProfileViewModel.successful.value = null
    }
}
