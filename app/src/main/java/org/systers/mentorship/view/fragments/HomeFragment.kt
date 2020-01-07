package org.systers.mentorship.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentHomeBinding
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.HomeStatsItem
import org.systers.mentorship.utils.StatsItemDecoration
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.AchievementsAdapter
import org.systers.mentorship.view.adapters.StatsAdapter
import org.systers.mentorship.viewmodels.HomeViewModel

/**
 * The fragment is responsible for showing a welcoming message and show some statistics of the User
 * usage of the app
 */
class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var achievementsAdapter: AchievementsAdapter
    private lateinit var statsAdapter: StatsAdapter

    private val activityCast by lazy { activity as MainActivity }

    companion object {
        /**
         * Creates an instance of HomeFragment
         */
        fun newInstance() = HomeFragment()

        val TAG: String = HomeFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_home
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achievementsAdapter = AchievementsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, linearLayoutManager.orientation)

        rvAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(divider)
        }

        statsAdapter = StatsAdapter(listOf())
        val gridLayoutManager = GridLayoutManager(context, 2)
        rvStats.apply {
            adapter = statsAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(StatsItemDecoration(8))
        }

        val space = 8
        rvStats.addItemDecoration(StatsItemDecoration(space))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        with(homeViewModel) {
            userStats.observe(viewLifecycleOwner, Observer { stats ->
                binding.stats = stats
                if (stats?.achievements?.isEmpty() != false) {
                    tvNoAchievements.visibility = View.VISIBLE
                    rvAchievements.visibility = View.GONE
                } else {
                    tvNoAchievements.visibility = View.GONE
                    rvAchievements.visibility = View.VISIBLE
                    achievementsAdapter.submitList(stats.achievements)



                    rvStats.adapter = StatsAdapter(createItems(stats))

                    runLayoutAnimation(rvStats)
                    runLayoutAnimation(rvAchievements)
                }
            })

            message.observe(viewLifecycleOwner, Observer { message ->
                Snackbar.make(homeContainer, message.toString(), Snackbar.LENGTH_SHORT).show()
            })
        }


        binding.btnAddTask.setOnClickListener {
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_add_task, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)

            AlertDialog.Builder(context).setTitle(baseActivity.getString(R.string.add_new_task))
                .setView(dialogLayout)
                .setPositiveButton(baseActivity.getString(R.string.save)) { _, _ ->
                    val newTask: String = editText.text.toString()
                    homeViewModel.addTask(newTask)
                }.setNegativeButton(baseActivity.getString(R.string.cancel)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }.show()
        }
    }

    private fun createItems(stats: HomeStatistics): List<HomeStatsItem> {
        return listOf(HomeStatsItem(
            getString(R.string.pending_requests),
            stats.pendingRequests,
            R.drawable.ic_clock_white_24dp,
            activityCast::showRequestsFragment
        ), HomeStatsItem(
            getString(R.string.accepted_requests),
            stats.acceptedRequests,
            R.drawable.ic_check_white_24dp,
            activityCast::showRequestsFragment
        ), HomeStatsItem(
            getString(R.string.rejected_requests),
            stats.rejectedRequests,
            R.drawable.ic_close_white_24dp,
            activityCast::showRequestsFragment
        ), HomeStatsItem(
            getString(R.string.completed_relations),
            stats.completedRelations,
            R.drawable.ic_done_all_white_24dp
        ) { activityCast.showRequestsFragment() })
    }

    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context
        recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context, R.anim.layout_fall_down
        )
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }
}

