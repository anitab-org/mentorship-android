package org.anitab.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.anitab.mentorship.R
import org.anitab.mentorship.databinding.ListMemberItemBinding
import org.anitab.mentorship.models.User

class MemberPagingAdapter(
    private val openDetailFunction: (memberId: Int, sharedImageView: ImageView, sharedTextView: TextView) -> Unit
) : PagingDataAdapter<User, MemberPagingAdapter.MemberViewHolder>(MemberDiffUtilCallback()) {

    private var lastPosition = -1

    override fun onBindViewHolder(holder: MemberPagingAdapter.MemberViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberPagingAdapter.MemberViewHolder {
        return MemberViewHolder(
            ListMemberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class MemberViewHolder(
        private val binding: ListMemberItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User?, position: Int) {
            data?.let {
                binding.apply {
                    user = data
                    executePendingBindings()
                    listMemberItemContainer.setOnClickListener {
                        openDetailFunction(data.id, circleImageView, tvName)
                    }

                    val animation = AnimationUtils.loadAnimation(
                        root.context,
                        if (position > lastPosition) R.anim.bottom_to_top else R.anim.top_to_bottom
                    )
                    itemView.startAnimation(animation)
                    lastPosition = position
                }
            }
        }
    }
}

private class MemberDiffUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}

