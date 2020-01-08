package org.systers.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_members.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.activities.MemberProfileActivity
import org.systers.mentorship.view.adapters.MembersAdapter
import org.systers.mentorship.viewmodels.MembersViewModel
import org.systers.mentorship.vo.ErrorResource
import org.systers.mentorship.vo.LoadingResource
import org.systers.mentorship.vo.SuccessResource

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

        membersViewModel = ViewModelProvider(this).get(MembersViewModel::class.java)
        membersViewModel.users.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is SuccessResource -> {
                    rvMembers.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = MembersAdapter(resource.data, openUserProfile)
                        visibility = View.VISIBLE
                    }
                    tvEmptyList.visibility = View.GONE
                }
                is LoadingResource -> {
                    tvEmptyList.text = getString(R.string.empty_members_list)
                    tvEmptyList.text = "Wow, such empty. But it won't be for long!"
                    rvMembers.visibility = View.GONE
                }
                is ErrorResource -> {
                    tvEmptyList.text = "An error occurred. status ${resource.message}"
                    rvMembers.visibility = View.GONE
                }
            }
        })
    }

    private val openUserProfile: (Int) -> Unit =
        { memberId ->
            val intent = Intent(activity, MemberProfileActivity::class.java)
            intent.putExtra(Constants.MEMBER_USER_ID, memberId)
            startActivity(intent)
        }
}
