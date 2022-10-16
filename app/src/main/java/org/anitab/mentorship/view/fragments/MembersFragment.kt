package org.anitab.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.fragment_members.fabFilter
import kotlinx.android.synthetic.main.fragment_members.rvMembers
import kotlinx.android.synthetic.main.fragment_members.srlMembers
import kotlinx.android.synthetic.main.fragment_members.tvEmptyList
import kotlinx.coroutines.launch
import org.anitab.mentorship.Injection
import org.anitab.mentorship.R
import org.anitab.mentorship.models.User
import org.anitab.mentorship.utils.Constants
import org.anitab.mentorship.utils.RecyclerViewItemDecoration
import org.anitab.mentorship.view.activities.MemberProfileActivity
import org.anitab.mentorship.view.adapters.MemberLoadingStateAdapter
import org.anitab.mentorship.view.adapters.MemberPagingAdapter
import org.anitab.mentorship.viewmodels.ListFilter
import org.anitab.mentorship.viewmodels.MembersViewModel

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

    private lateinit var memberAdapter: MemberPagingAdapter

    private val membersViewModel: MembersViewModel by lazy {
        requireActivity().run {
            ViewModelProviders.of(
                this@MembersFragment,
                Injection.provideViewModelFactory(requireContext())
            ).get(MembersViewModel::class.java)
        }
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        // initializing MemberPagingAdaptermember
        memberAdapter = MemberPagingAdapter(::openUserProfile)

        // calling adapter and livedata observer methods
        setUpAdapter()
        setObserver()

        // setting member list refresh listener
        srlMembers.setOnRefreshListener { memberAdapter.refresh() }

        // setting member list filter option fab
        with(fabFilter) {
            setOnClickListener { view -> view?.let { showListFilterPopup(it) } }
            show()
        }
    }

    // Setting up member list recyclerview adapter and item decor
    private fun setUpAdapter() {
        rvMembers.apply {
            addItemDecoration(RecyclerViewItemDecoration())
            adapter =
                memberAdapter.withLoadStateFooter(footer = MemberLoadingStateAdapter { memberAdapter.retry() })
        }
    }

    // Setting observer to viewmodel to listen changes in member list livedata
    private fun setObserver() {
        lifecycleScope.launch {
            lifecycle.whenStarted {
                showMemberListLoadingState()

                membersViewModel.userList.observe(viewLifecycleOwner) { data ->
                    memberAdapter.submitData(viewLifecycleOwner.lifecycle, data)
                }
            }
        }
    }

    // Setting member list loading states
    private fun showMemberListLoadingState() {
        memberAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                srlMembers.isRefreshing = memberAdapter.snapshot().isEmpty()
                tvEmptyList.isVisible = false
            } else {
                srlMembers.isRefreshing = false

                val error = loadState.let { state ->
                    when {
                        state.prepend is LoadState.Error -> state.prepend as LoadState.Error
                        state.append is LoadState.Error -> state.append as LoadState.Error
                        state.refresh is LoadState.Error -> state.refresh as LoadState.Error
                        else -> null
                    }
                }

                error?.let {
                    if (memberAdapter.snapshot().isEmpty()) {
                        tvEmptyList.apply {
                            isVisible = true
                            text = it.error.message ?: "No such users available"
                        }
                    }
                }
            }
        }
    }

    // On click method on recyclerview member item
    private fun openUserProfile(member: User?) {
        member?.let {
            val intent = Intent(activity, MemberProfileActivity::class.java)
            intent.putExtra(Constants.MEMBER_USER_EXTRAS, it)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    // Show member list filter option pop-up
    private fun showListFilterPopup(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.menu_members)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.allMembers -> membersViewModel.getFilteredUserList(ListFilter.NO_FILTER)
                R.id.availableToMentor -> membersViewModel.getFilteredUserList(ListFilter.AVAILABLE_TO_MENTOR)
                R.id.needMentoring -> membersViewModel.getFilteredUserList(ListFilter.NEED_MENTORING)
                R.id.haveSkill -> membersViewModel.getFilteredUserList(ListFilter.HAVE_SKILL)
            }
            true
        }
        popup.show()
    }
}
