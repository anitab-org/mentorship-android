package org.systers.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
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

    private lateinit var membersViewModel: MembersViewModel
    private var membersAdapter: MembersAdapter = MembersAdapter(arrayListOf(), ::openUserProfile)

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        membersViewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)
        membersViewModel.successful.observe(this, Observer { successful ->
            (activity as MainActivity).hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    if (membersViewModel.userList.isEmpty()) {
                        tvEmptyList.text = getString(R.string.empty_members_list)
                        rvMembers.visibility = View.GONE
                    } else {
                        membersAdapter = MembersAdapter(membersViewModel.userList.toMutableList(), ::openUserProfile)

                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = membersAdapter
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
    }

    private fun openUserProfile(memberId: Int) {
            val intent = Intent(activity, MemberProfileActivity::class.java)
            intent.putExtra(Constants.MEMBER_USER_ID, memberId)
            startActivity(intent)
        }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_members, menu)

        val searchItem = menu?.findItem(R.id.menu_search_members)
        val searchView = searchItem?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(text: String?): Boolean {
                membersAdapter.filter.filter(text)
                return true
            }
        })

        searchView?.setOnQueryTextFocusChangeListener { _, focused ->
            if (!focused) {
                searchItem?.collapseActionView()
                searchView.setQuery("", false)
            }
        }
    }
}
