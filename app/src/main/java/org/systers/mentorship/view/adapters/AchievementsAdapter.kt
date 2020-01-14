package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievement_list_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Task

/**
 * This class represents the adapter that fills in each view of the Achievements recyclerView
 * @param achievementsList list of achievements of the user
 */
class AchievementsAdapter(
        private val achievementsList: List<Task>
) : RecyclerView.Adapter<AchievementsAdapter.AchievementViewHolder>() {

    val context = MentorshipApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsAdapter.AchievementViewHolder =
         AchievementViewHolder(
                 LayoutInflater.from(parent.context)
                         .inflate(R.layout.achievement_list_item, parent, false))


    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        val item = achievementsList[position]
        val itemView = holder.itemView

        itemView.checkBox.text = item.description
    }

    override fun getItemCount(): Int = achievementsList.size

    class AchievementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

