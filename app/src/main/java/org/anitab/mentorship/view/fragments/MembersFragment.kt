package org.anitab.mentorship.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.fragment_members.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.anitab.mentorship.Injection
import org.anitab.mentorship.R
import org.anitab.mentorship.utils.Constants
import org.anitab.mentorship.utils.Constants.FILTER_MAP
import org.anitab.mentorship.utils.Constants.FILTER_REQUEST_CODE
import org.anitab.mentorship.utils.Constants.SORT_KEY
import org.anitab.mentorship.utils.RecyclerViewItemDecoration
import org.anitab.mentorship.view.activities.FilterActivity
import org.anitab.mentorship.view.activities.MemberProfileActivity
import org.anitab.mentorship.view.adapters.MemberLoadingStateAdapter
import org.anitab.mentorship.view.adapters.MemberPagingAdapter
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

    private val membersViewModel: MembersViewModel by lazy {
        requireActivity().run {
            ViewModelProviders.of(
                this@MembersFragment,
                Injection.provideViewModelFactory(requireContext())
            ).get(MembersViewModel::class.java)
        }
    }
    private lateinit var memberAdapter: MemberPagingAdapter
    private var isLoading = false
    private var filterMap = hashMapOf(SORT_KEY to SortValues.REGISTRATION_DATE.name)

    override fun getLayoutResourceId(): Int = R.layout.fragment_members

    @OptIn(ExperimentalPagingApi::class)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        memberAdapter = MemberPagingAdapter(::openUserProfile)

        srlMembers.setOnRefreshListener {
            memberAdapter.refresh()
            if (!isLoading) {
                fetchNewest(true)
                isLoading = true
            }
        }

        setUpAdapter()
        setObserver()
    }

    private fun setUpAdapter() {
        rvMembers.apply {
            addItemDecoration(RecyclerViewItemDecoration())
            adapter =
                memberAdapter.withLoadStateFooter(footer = MemberLoadingStateAdapter { memberAdapter.retry() })
        }
    }

    @ExperimentalPagingApi
    private fun setObserver() {
        lifecycleScope.launch {
            lifecycle.whenStarted {
                membersViewModel.getUsersLiveData().collect { data ->
                    memberAdapter.submitData(viewLifecycleOwner.lifecycle, data)

                    showMemberListLoadingState()
                }
            }
        }
    }

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
                            text = it.error.message
                        }
                    }
                }
            }
        }
    }

    private fun openUserProfile(
        memberId: Int,
        sharedImageView: ImageView,
        sharedTextView: TextView
    ) {
        val intent = Intent(activity, MemberProfileActivity::class.java)
        intent.putExtra(Constants.MEMBER_USER_ID, memberId)
        val imgAnim = Pair.create<View, String>(
            sharedImageView,
            ViewCompat.getTransitionName(sharedTextView)!!
        )

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(baseActivity, imgAnim)

        startActivity(intent, options.toBundle())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                val intent = Intent(context, FilterActivity::class.java)
                intent.putExtra(FILTER_MAP, filterMap)
                startActivityForResult(intent, FILTER_REQUEST_CODE)
                activity?.overridePendingTransition(
                    R.anim.anim_slide_from_bottom,
                    R.anim.anim_stay
                )
                true
            }
            R.id.menu_refresh -> {
                fetchNewest(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            filterMap = data?.extras?.get(FILTER_MAP) as HashMap<String, String>?
                    ?: hashMapOf(SORT_KEY to SortValues.REGISTRATION_DATE.name)
            rvAdapter.updateUsersList(filterMap, membersViewModel.userList)
        }
    }*/

    enum class SortValues {
        NAMEAZ,
        NAMEZA,
        REGISTRATION_DATE
    }

    private fun fetchNewest(isRefresh: Boolean) {
        srlMembers.isRefreshing = isRefresh
        membersViewModel.getUsers(isRefresh)
    }
}
