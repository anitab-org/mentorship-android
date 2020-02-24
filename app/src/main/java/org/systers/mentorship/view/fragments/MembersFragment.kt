package org.systers.mentorship.view.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.FILTER_MAP
import org.systers.mentorship.utils.Constants.FILTER_REQUEST_CODE
import org.systers.mentorship.utils.Constants.SORT_KEY
import org.systers.mentorship.view.activities.FilterActivity
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
    private lateinit var rvAdapter: MembersAdapter
    private var filterMap = hashMapOf(SORT_KEY to SortValues.REGISTRATION_DATE.name)

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_members, menu)
        menu.findItem(R.id.search_item)?.let { searchItem ->
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
        setHasOptionsMenu(true)
        rvAdapter = MembersAdapter(listOf(), openUserProfile)
        srlMembers.setOnRefreshListener { fetchNewest() }

        membersViewModel.successful.observe(this, Observer { successful ->
            srlMembers.isRefreshing = false
            if (successful != null) {
                if (successful) {
                    rvAdapter.setData(membersViewModel.userList)
                    rvAdapter.filter(filterMap)

                    if (membersViewModel.userList.isEmpty()) {
                        tvEmptyList.text = getString(R.string.empty_members_list)
                        rvMembers.visibility = View.GONE
                    } else {
                        rvMembers.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = rvAdapter
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

        fetchNewest()
    }

    private val openUserProfile: (Int) -> Unit =
            { memberId ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                startActivity(intent)
            }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                val intent = Intent(context, FilterActivity::class.java)
                intent.putExtra(FILTER_MAP, filterMap)
                startActivityForResult(intent, FILTER_REQUEST_CODE)
                activity?.overridePendingTransition(
                        R.anim.anim_slide_from_bottom,
                        R.anim.anim_stay)
                true
            }
            R.id.menu_refresh -> {
                fetchNewest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            filterMap = data?.extras?.get(FILTER_MAP) as HashMap<String, String>?
                    ?: hashMapOf(SORT_KEY to SortValues.REGISTRATION_DATE.name)
            rvAdapter.filter(filterMap)
        }
    }

    enum class SortValues {
        NAMEAZ,
        NAMEZA,
        REGISTRATION_DATE
    }

    private fun fetchNewest()  {
        srlMembers.isRefreshing = true
        membersViewModel.getUsers()
    }

}
