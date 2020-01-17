package org.systers.mentorship.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.Constants
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
    }

    private lateinit var membersViewModel: MembersViewModel

    private lateinit var usersList: List<User>
    private lateinit var currentUserList: List<User>
    private lateinit var currentFilterOptions: ArrayList<Int>
    private var currentSortedItem = -1

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
                        usersList = membersViewModel.userList
                        currentUserList = membersViewModel.userList
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(currentUserList, openUserProfile)
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

        //initialize currentFilterOptions array and set all values of currentChosenFilterOptions (used for dialog box) to false
        val currentChosenFilterOptions = BooleanArray(resources.getStringArray(R.array.filter_members_options).size) { i -> false}
        currentFilterOptions = arrayListOf()

        fabFilterListButton.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.filter_members)
                    .setMultiChoiceItems(R.array.filter_members_options, currentChosenFilterOptions) { dialog, which, isChecked ->
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            currentChosenFilterOptions[which] = true
                            currentFilterOptions.add(which)
                        } else if (currentFilterOptions.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            currentChosenFilterOptions[which] = false
                            currentFilterOptions.remove(Integer.valueOf(which))
                        }
                    }
                    .setPositiveButton(R.string.ok) {dialog,_ ->
                        setCurrentFilter(true)
                        dialog.dismiss()
                    }
            builder.create().show()
        }

        fabSortListButton.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.sort_members)
                    .setSingleChoiceItems(R.array.sort_members_options, currentSortedItem) {dialog, which ->
                        currentSortedItem = which
                        setCurrentFilter(true)
                        dialog.dismiss()
                    }
                    .setPositiveButton(R.string.cancel) {dialog,_ ->
                        currentSortedItem = -1
                        setCurrentFilter(false)
                        dialog.dismiss()
                    }
            builder.create().show()
        }
    }

    private fun setCurrentFilter(callSort: Boolean) {
        currentUserList = usersList
        for (i in currentFilterOptions)
        {
            if (i == 0)
                   currentUserList = currentUserList.filter { i -> i.isAvailableToMentor == true && i.needsMentoring == false }
            if (i == 1)
                currentUserList = currentUserList.filter { i -> i.needsMentoring == true && i.isAvailableToMentor == false }
        }
        if (callSort)
        {
            if (currentSortedItem == 0)
                currentUserList = currentUserList.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name!! })
            else if (currentSortedItem == 1)
                currentUserList = currentUserList.reversed()
        }
        rvMembers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MembersAdapter(currentUserList, openUserProfile)
        }
    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }
}
