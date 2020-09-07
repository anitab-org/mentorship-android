package org.systers.mentorship.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Comments

class CommentsAdapter(
        private val context: Context,
        private val commentsList: List<Comments>
): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item, parent, false))

    override fun getItemCount(): Int = commentsList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = commentsList[position]
        val itemView = holder.itemView
        itemView.commentContent.text = item.comment
    }

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}