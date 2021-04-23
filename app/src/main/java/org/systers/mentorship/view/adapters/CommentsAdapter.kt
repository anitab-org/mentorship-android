package org.systers.mentorship.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.systers.mentorship.databinding.CommentListItemBinding
import org.systers.mentorship.models.Comment
import org.systers.mentorship.models.CommentDisplayModel

class CommentsAdapter(
        private val context: Context,
        private val commentList: List<CommentDisplayModel>
): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val binding: CommentListItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
                CommentListItemBinding.inflate(
                        LayoutInflater.from(context)
                )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.binding.comment = comment
    }

    override fun getItemCount(): Int = commentList.size
}