package org.systers.mentorship.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_task_detail.*
import kotlinx.android.synthetic.main.comment_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Comments
import org.systers.mentorship.models.User
import org.systers.mentorship.viewmodels.MemberProfileViewModel

class CommentsAdapter(
        private val context: Context,
        private val commentsList: List<Comments>,
        private val progressBar: ProgressBar
): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    val memberProfileViewModel = MemberProfileViewModel()
    var userNames = mutableSetOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item, parent, false))

    override fun getItemCount(): Int = commentsList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = commentsList[position]
        memberProfileViewModel.userId = item.user_id
        memberProfileViewModel.getUserProfile()
        val itemView = holder.itemView
        memberProfileViewModel.successful.observe(itemView.context as LifecycleOwner, Observer {
            successful ->
            if (successful != null) {
                if (successful) {
                    itemView.commentAuthor.text = memberProfileViewModel.userProfile.name
                    if (!userNames.contains(memberProfileViewModel.userProfile)) {
                        userNames.add(memberProfileViewModel.userProfile)
                    }
                    for (user in userNames) {
                        if (item.user_id == user.id) itemView.commentAuthor.text = user.name
                    }
                    progressBar.visibility = View.GONE
                }
                else itemView.commentAuthor.text = "Failed to Retrieve author"
            }
        })
        itemView.commentContent.text = item.comment
    }

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}