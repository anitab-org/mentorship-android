package org.anitab.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import org.anitab.mentorship.databinding.ListMemberNetworkStateItemBinding

class MemberLoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MemberLoadingStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: MemberLoadingStateAdapter.LoadStateViewHolder,
        loadState: LoadState
    ) {

        holder.binding.apply {
            when (loadState) {
                is LoadState.Loading -> {
                    pbMembersListState.isVisible = true
                    btnRetry.isVisible = false
                    tvErrorMsg.isVisible = false
                }
                is LoadState.Error -> {
                    pbMembersListState.isVisible = false
                    btnRetry.isVisible = true
                    tvErrorMsg.apply {
                        isVisible = true
                        text = loadState.error.message
                    }
                }
                else -> pbMembersListState.isVisible = true
            }

            btnRetry.setOnClickListener { retry.invoke() }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MemberLoadingStateAdapter.LoadStateViewHolder {
        return LoadStateViewHolder(
            ListMemberNetworkStateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class LoadStateViewHolder(val binding: ListMemberNetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
