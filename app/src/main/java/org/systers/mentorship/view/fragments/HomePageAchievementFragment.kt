package org.systers.mentorship.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home_page_achievement.*

import org.systers.mentorship.R
import org.systers.mentorship.models.Task
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.view.adapters.AchievementsAdapter

/**
 * A simple [Fragment] subclass.
 */
class HomePageAchievementFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [HomePageAchievementFragment]
         */
        fun newInstance(achievementsList: List<Task>) : BaseFragment {

            val args = Bundle()
            args.putParcelableArrayList(Constants.ACHIEVEMENTS_LIST, ArrayList(achievementsList))

            val frag = HomePageAchievementFragment()
            frag.arguments = args

            return frag
        }
    }

    private lateinit var achievementsList: MutableList<Task>

    private lateinit var achievementsAdapter: AchievementsAdapter

    override fun getLayoutResourceId(): Int{
        return R.layout.fragment_home_page_achievement
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        achievementsAdapter = AchievementsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, linearLayoutManager.orientation)

        rvaAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(divider)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            achievementsList = it.getParcelableArrayList(Constants.ACHIEVEMENTS_LIST)
        }
        setView()
    }

    private fun setView() {
        if (achievementsList.isEmpty()) {
            tvaNoAchievements.visibility = View.VISIBLE
            rvaAchievements.visibility = View.GONE
        } else {
            tvaNoAchievements.visibility = View.GONE
            rvaAchievements.visibility = View.VISIBLE
            achievementsAdapter.submitList(achievementsList)
        }
    }
}
