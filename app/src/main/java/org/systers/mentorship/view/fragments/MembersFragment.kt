package org.systers.mentorship.view.fragments

import android.app.Activity.RESULT_OK
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.REQUEST_CODE_FILTER_ACTIVITY
import org.systers.mentorship.view.activities.FilterActivity
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.activities.MemberProfileActivity
import org.systers.mentorship.view.adapters.MembersAdapter
import org.systers.mentorship.viewmodels.MembersViewModel

/**
 * The fragment is responsible for showing all the members of the system in a list format
 */
class MembersFragment: BaseFragment() {

    companion object {
        /**
         * Creates an instance of [MembersFragment]
         */
        fun newInstance() = MembersFragment()
        var filtersApplied= mutableMapOf<String,String?>()
    }

    private lateinit var membersViewModel: MembersViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        membersViewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)
        membersViewModel.successful.observe(this, Observer {
            successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    if (membersViewModel.userList.isEmpty()) {
                        tvEmptyList.text = getString(R.string.empty_members_list)
                        rvMembers.visibility = View.GONE
                    } else {
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(membersViewModel.userList, openUserProfile)
                        }
                        tvEmptyList.visibility = View.GONE
                    }
                } else {
                    view?.let {
                        Snackbar.make(it, membersViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        (activity as MainActivity).showProgressDialog(getString(R.string.fetching_users))
        membersViewModel.getUsers()

        fabAddFilter.setOnClickListener {
            startActivityForResult(Intent(context,FilterActivity::class.java),REQUEST_CODE_FILTER_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==REQUEST_CODE_FILTER_ACTIVITY && resultCode==RESULT_OK){
            /**
             * Checking if any filter is applied or there are no filters applied for any property.
             * If filter_status==true, that means no filter is applied and the whole list of members will be displayed.
             * */
            var filterStatus=false
            for (key in filtersApplied.keys) {
                if (filtersApplied[key] != null) {
                    filterStatus = true
                    break
                }
            }

            if (filterStatus){

                val filteredList = membersViewModel.userList.filter {
                    /**
                     * Matching the filter applied with each user's data
                     * If the user's data is not null and there is a filter applied for that field(filter not being null), in that case it will
                     * check whether the user's data matches with the filter or not, else
                     * it will return false
                     * */
                    val filterName = if (it.name!=null && filtersApplied[context?.getString(R.string.name)] != null) it.name?.contains(filtersApplied[context?.getString(R.string.name)].toString()) else false

                    val filterUsername = if (it.username!=null && filtersApplied[context?.getString(R.string.username)] != null) it.username?.contains(filtersApplied[context?.getString(R.string.username)].toString()) else false

                    val filterSlackUsername = if (it.slackUsername!=null && filtersApplied[context?.getString(R.string.slack_username)] != null) it.slackUsername?.contains(filtersApplied[context?.getString(R.string.slack_username)].toString()) else false

                    val filterInterests = if (it.interests!=null && filtersApplied[context?.getString(R.string.interests)] != null) it.interests?.contains(filtersApplied[context?.getString(R.string.interests)].toString()) else false

                    val filterBio = if (it.bio!=null && filtersApplied[context?.getString(R.string.bio)] != null) it.bio?.contains(filtersApplied[context?.getString(R.string.bio)].toString()) else false

                    val filterLocation = if (it.location!=null && filtersApplied[context?.getString(R.string.location)] != null) it.location?.contains(filtersApplied[context?.getString(R.string.location)].toString()) else false

                    val filterOccupation = if (it.occupation!=null && filtersApplied[context?.getString(R.string.occupation)] != null) it.occupation?.contains(filtersApplied[context?.getString(R.string.occupation)].toString()) else false

                    val filterOrganization = if (it.organization!=null && filtersApplied[context?.getString(R.string.organization)] != null) it.organization?.contains(filtersApplied[context?.getString(R.string.organization)].toString()) else false

                    val filterSkills = if (it.skills!=null && filtersApplied[context?.getString(R.string.skills)] != null) it.skills?.contains(filtersApplied[context?.getString(R.string.skills)].toString()) else false

                    val filterAvailableToMentor = if (it.isAvailableToMentor!=null && filtersApplied[context?.getString(R.string.available_to_mentor)] != null) it.isAvailableToMentor.toString()== filtersApplied[context?.getString(R.string.available_to_mentor)] else false

                    val filterNeedsMentoring = if (it.needsMentoring!=null && filtersApplied[context?.getString(R.string.need_mentoring)] != null) it.needsMentoring.toString()== filtersApplied[context?.getString(R.string.need_mentoring)] else false

                    /**
                     * Filter :  whether the user data matches the filters applied or not
                     * If true, that user's data will be displayed on the Member's tab.
                     * */
                    filterName!! || filterUsername!! || filterSlackUsername!! || filterInterests!! || filterBio!!
                            || filterLocation!! || filterOccupation!! || filterOrganization!! || filterSkills!!
                            || filterAvailableToMentor || filterNeedsMentoring
                }

                /**
                 * Re-applying the adapter to the recycler view with the new filtered list
                 * */
                rvMembers.apply {
                    adapter = MembersAdapter(filteredList, openUserProfile)
                }
            }else{
                rvMembers.apply {
                    /**
                     * Re-applying the entire members list when no filters are applied.
                     * */
                    adapter = MembersAdapter(membersViewModel.userList, openUserProfile)
                }
            }
        }
    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }
}
