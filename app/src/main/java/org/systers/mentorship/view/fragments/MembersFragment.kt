package org.systers.mentorship.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.core.app.ActivityOptionsCompat
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

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        swipeRefreshLayout.setOnRefreshListener {
            (activity as MainActivity).showProgressDialog(getString(R.string.fetching_users))
            membersViewModel.getUsers()
        }

        membersViewModel = ViewModelProviders.of(this).get(MembersViewModel::class.java)
        membersViewModel.successful.observe(this, Observer { successful ->
            (activity as MainActivity).hideProgressDialog()
            swipeRefreshLayout.isRefreshing = false
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

    private val openUserProfile: (Int, () -> View) -> Unit =
            { memberId, getView ->
                val intent = Intent(activity, MemberProfileActivity::class.java)
                intent.putExtra(Constants.MEMBER_USER_ID, memberId)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
                        getView(), getString(R.string.profile_image)).toBundle()
                startActivity(intent, options)
                activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_members, menu)
    }

}
