package org.systers.mentorship.view.fragments

import android.content.DialogInterface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.MentorshipApplication
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
    private lateinit var changedList: List<User>
    private var filterCheckedItem = -1
    private var sortCheckedItem = -1

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
                        changedList = membersViewModel.userList
                        filterUsers()
                        sortUsers()
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

    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }

    private fun sortUsers(){
        fabSortUsers.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle(getString(R.string.filter_title))
            var sortOptions = arrayOf<CharSequence>(getString(R.string.sort_alphabetical), getString(R.string.sort_date))
            builder.setSingleChoiceItems(sortOptions, sortCheckedItem, DialogInterface.OnClickListener { dialog, item ->
                when (item) {
                    0 ->{ //sort alphabetically
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.sort_alphabetical), Toast.LENGTH_LONG).show()
                        val newList = changedList.sortedBy {
                            it.name
                        }
                        changedList = newList
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(newList, openUserProfile)
                        }
                    }
                    1 ->{ //sort by join date(default)
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.sort_date), Toast.LENGTH_LONG).show()
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(membersViewModel.userList, openUserProfile)
                        }
                        changedList = membersViewModel.userList
                    }
                }
                sortCheckedItem = item
                dialog.dismiss()
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun filterUsers(){
        fabFilterUsers.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle(getString(R.string.filter_title))
            val filterOptions = arrayOf<CharSequence>(
                    getString(R.string.mentor), getString(R.string.mentee),
                    getString(R.string.mentor_and_mentee), getString(R.string.clear)
            )
            builder.setSingleChoiceItems(filterOptions, filterCheckedItem, DialogInterface.OnClickListener { dialog, item ->
                when (item) {
                    0 ->{ //for mentor
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.mentor), Toast.LENGTH_LONG).show()
                        val newList = changedList.filter {
                            it.isAvailableToMentor ?: false //assign false by default if value is null
                        }
                        changedList = newList
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(newList, openUserProfile)
                        }
                    }
                    1 ->{ //for mentee
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.mentee), Toast.LENGTH_LONG).show()
                        val newList = changedList.filter {
                            it.needsMentoring ?: false //assign false by default if value is null
                        }
                        changedList = newList
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(newList, openUserProfile)
                        }
                    }
                    2 ->{ //for mentor and mentee
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.mentor_and_mentee), Toast.LENGTH_LONG).show()
                        val newList = changedList.filter {
                            it.needsMentoring ?: false && it.isAvailableToMentor ?: false
                        }
                        changedList = newList
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(newList, openUserProfile)
                        }
                    }
                    3 ->{ //clear filter
                        Toast.makeText(MentorshipApplication.getContext(), getString(R.string.clear), Toast.LENGTH_LONG).show()
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MembersAdapter(membersViewModel.userList, openUserProfile)
                        }
                        changedList = membersViewModel.userList
                    }
                }
                filterCheckedItem = item
                dialog.dismiss()
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}
