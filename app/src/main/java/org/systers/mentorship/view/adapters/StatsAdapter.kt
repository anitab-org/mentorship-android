package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_statistics.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.HomeStatsItem


class StatsAdapter(private val items: List<HomeStatsItem>) :
    RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    private val gradients: MutableList<Int> = mutableListOf(
        R.drawable.gradient_1, R.drawable.gradient_2, R.drawable.gradient_3, R.drawable.gradient_4
    ).apply {
        shuffle()
    }

    var currentGradients = gradients

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.card_statistics, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val itemView = holder.itemView

        itemView.tvMainText.text = item.text
        itemView.tvNumber.text = item.count.toString()
        itemView.imgIcon.setImageResource(item.icon)
        itemView.cardConstraintLayout.setOnClickListener { items[holder.adapterPosition].actionFun() }

        if (currentGradients.size == 0) currentGradients = gradients

        itemView.cardConstraintLayout.setBackgroundResource(currentGradients.last())

        currentGradients = currentGradients.dropLast(1).toMutableList()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
