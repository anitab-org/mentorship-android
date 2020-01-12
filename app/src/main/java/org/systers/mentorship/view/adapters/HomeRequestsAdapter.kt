package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.reuqests_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.view.activities.MainActivity

class HomeRequestsAdapter(
        private var requestsList: MutableList<Int>
) : RecyclerView.Adapter<HomeRequestsAdapter.HomeRequestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            HomeRequestsViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.reuqests_item, parent, false))

    override fun onBindViewHolder(holder: HomeRequestsViewHolder, position: Int) {
        with(holder.itemView) {
            when (position) {
                0 -> {
                    tvRequestItemTitle.setText(R.string.pending_requests)
                    tvRequestItemCount.text = requestsList[0].toString()
                }
                1 -> {
                    tvRequestItemTitle.setText(R.string.accepted_requests)
                    tvRequestItemCount.text = requestsList[1].toString()
                }
                2 -> {
                    tvRequestItemTitle.setText(R.string.rejected_requests)
                    tvRequestItemCount.text = requestsList[2].toString()
                }
                3 -> {
                    tvRequestItemTitle.setText(R.string.completed_relations)
                    tvRequestItemCount.text = requestsList[3].toString()
                }
            }
            setOnClickListener {
                MainActivity.instance.showRequestsFragment()
            }
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_appear_from_right))
        }
    }

    // we have only 4 elements: pending, accepted, rejected requests and completed relations
    override fun getItemCount() = 4

    fun setData(requestsList: MutableList<Int>) {
        this.requestsList = requestsList
        notifyDataSetChanged()
    }

    class HomeRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}