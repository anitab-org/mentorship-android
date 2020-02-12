package org.systers.mentorship.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.activities.MemberProfileActivity
import org.systers.mentorship.view.adapters.MembersAdapter
import org.systers.mentorship.viewmodels.MembersViewModel

/**
 * The fragment is responsible for showing all the members of the system in a list format
 */
class MembersFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [MembersFragment]
         */
        fun newInstance() = MembersFragment()
    }

    private val membersViewModel by lazy {
        ViewModelProviders.of(this).get(MembersViewModel::class.java)
    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }

    private val membersAdapter = MembersAdapter(openDetailFunction = openUserProfile)

    private var successfulRequestCount = 0

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setHasOptionsMenu(true)

        rvMembers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = membersAdapter
        }

        membersViewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)
        membersViewModel.successful.observe(this, Observer { successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    if (membersViewModel.userList.isEmpty()) {
                        tvEmptyList.text = getString(R.string.empty_members_list)
                        rvMembers.visibility = View.GONE
                    } else {
                        successfulRequestCount++
                        if (successfulRequestCount == 1) membersAdapter.initialUsers = membersViewModel.userList

                        membersAdapter.currentUsers = membersViewModel.userList
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_members, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fun showFilterDialog() {
            val view = layoutInflater.inflate(R.layout.dialog_filter_members, null, false)

            val cbNeedsMentoring: CheckBox = view.findViewById(R.id.cbNeedsMentoring)
            val cbAvailableToMentor: CheckBox = view.findViewById(R.id.cbAvailableToMentor)
            val etSkills: EditText = view.findViewById(R.id.etSkills)

            cbNeedsMentoring.isChecked = membersAdapter.filter.needsMentoring
            cbAvailableToMentor.isChecked = membersAdapter.filter.availableToMentor
            etSkills.setText(membersAdapter.filter.skills)

            AlertDialog.Builder(baseActivity)
                    .setTitle(R.string.filter_by)
                    .setView(view)
                    .setPositiveButton(R.string.filter_by) { dialog, _ ->
                        val needsMentoring = cbNeedsMentoring.isChecked
                        val availableToMentor = cbAvailableToMentor.isChecked
                        val skills = etSkills.text.toString()


                        val filter = MembersAdapter.Filter(needsMentoring, availableToMentor, skills)
                        membersAdapter.filter = filter

                        dialog.dismiss()
                    }
                    .show()
        }

        fun showSortDialog() {
            val items = arrayOf(
                    getString(R.string.name_descending),
                    getString(R.string.name_ascending),
                    getString(R.string.creation_date_ascending),
                    getString(R.string.creation_date_descending)
            )

            AlertDialog.Builder(baseActivity)
                    .setTitle(R.string.sort_by)
                    .setSingleChoiceItems(items, membersAdapter.sort.number) { dialog, i ->
                        val sort = MembersAdapter.Sort.fromInt(i)
                                ?: MembersAdapter.Sort.NameAscending
                        membersAdapter.sort = sort
                        dialog.dismiss()
                    }
                    .show()
        }

        return when (item.itemId) {
            R.id.menu_filter -> {
                showFilterDialog()
                true
            }
            R.id.menu_sort -> {
                showSortDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
