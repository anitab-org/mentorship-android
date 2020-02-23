package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.SearchView
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

    private val membersViewModel by lazy {
        ViewModelProviders.of(this).get(MembersViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        var searchItem=menu.findItem(R.id.search_item) as MenuItem
        var searchView=searchItem.actionView as SearchView
        searchView.queryHint="Search members"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                searchUsers(newText)
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }
    fun searchUsers(query: String){
        var userList=mutableListOf<User>()
        for(user in membersViewModel.userList){
            // ""+ to convert String to CharSequence
            if ((""+user.username).contains(query, ignoreCase = true)){
                userList.add(user)
            }
        }
        rvMembers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MembersAdapter(userList, openUserProfile)
        }
        tvEmptyList.visibility = View.GONE
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
/*      User search working
        1. New menu_search.xml created having <item> with name "search_item" and actionViewClass "SearchView"
        2. onCreateOptionsMenu() imports SearchView and adds listener for searching.
        3. onQueryTextChange() calls searchUsers() function to search name.
 */
        setHasOptionsMenu(true)
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
    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }
}
