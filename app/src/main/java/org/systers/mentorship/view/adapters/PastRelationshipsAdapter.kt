package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.member_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User

class PastRelationshipsAdapter(
        private var pastRelationships: List<User>
) : RecyclerView.Adapter<PastRelationshipsAdapter.PastRelationshipsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PastRelationshipsViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.member_item, parent, false)
            )

    override fun onBindViewHolder(holder: PastRelationshipsViewHolder, position: Int) {
        val user = pastRelationships[position]

        with(holder.itemView) {
            if (!user.avatar.isNullOrBlank())
                Glide.with(MentorshipApplication.getContext()).load(user.avatar).into(ivMemberAvatar)
            tvMemberName.text = user.name
            tvMemberOccupation.text = user.occupation
            setOnClickListener {
                // TODO: Add PastRelationActivity
                Snackbar.make(this, R.string.not_implemented, Snackbar.LENGTH_SHORT).show()
            }
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_appear_from_right))
        }
    }

    override fun getItemCount() = pastRelationships.size

    fun setData(pastRelationships: List<User>) {
        this.pastRelationships = pastRelationships
        notifyDataSetChanged()
    }

    class PastRelationshipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}