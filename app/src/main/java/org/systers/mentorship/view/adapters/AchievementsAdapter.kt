package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.systers.mentorship.databinding.AchievementListItemBinding
import org.systers.mentorship.models.Task

/**
 * This class is the RecyclerView adapter for achievements. It is a subclass of [ListAdapter] for
 * easy async calculation of diffs using DiffUtil to provide nice animations when the data set
 * changes.
 */
class AchievementsAdapter : ListAdapter<Task, AchievementsAdapter.ViewHolder>(AchievementsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsAdapter.ViewHolder {
        val view = AchievementListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AchievementsAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * This class holds a view for each item of the Achievements list
     * @param itemView represents each view of achievements list
     */
    inner class ViewHolder(private val itemBinding: AchievementListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        /**
         * This function binds the description of the achievement to the textview
         * @param task The Achievement whose description is to be bound
         */
        fun bind(task: Task) {
            itemBinding.tvDescription.text = task.description
        }
    }

}

/**
 * This class represents the DiffCallback for tasks, and is used in [AchievementsAdapter]
 */
class AchievementsItemCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

}

