package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentHomeBinding
import org.systers.mentorship.view.adapters.AchievementsAdapter
import org.systers.mentorship.view.adapters.CustomExpandaleListAdapter
import org.systers.mentorship.viewmodels.HomeViewModel


/**
 * The fragment is responsible for showing a welcoming message and show some statistics of the User
 * usage of the app
 */
class HomeFragment : BaseFragment() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var achievementsAdapter: AchievementsAdapter
    // private lateinit var expandableListView: View
    private lateinit var expandaleListAdapter: CustomExpandaleListAdapter

    companion object {
        /**
         * Creates an instance of HomeFragment
         */
        fun newInstance() = HomeFragment()
        val TAG: String = HomeFragment::class.java.simpleName
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_home
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        srlHome.setOnRefreshListener { fetchNewest() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)


        with(homeViewModel) {
            userStats.observe(viewLifecycleOwner, Observer { stats ->
                srlHome.isRefreshing = false
                binding.stats = stats
                if (stats?.achievements?.isEmpty() != false) {
                    tvNoAchievements.visibility = View.VISIBLE
                    rvAchievements.visibility = View.GONE
                } else {
                    tvNoAchievements.visibility = View.GONE
                    rvAchievements.visibility = View.VISIBLE
                    achievementsAdapter.submitList(stats.achievements)
                }
            })

            message.observe(viewLifecycleOwner, Observer { message ->
                Snackbar.make(homeContainer, message.toString(), Snackbar.LENGTH_SHORT).show()
            })

            dashboardStats.observe(viewLifecycleOwner, Observer { dashboardstats ->
                //srlHome.isRefreshing = false
                expandaleListAdapter = CustomExpandaleListAdapter(dashboardstats, context)
                expandablelvrequests.apply {
                    setAdapter(CustomExpandaleListAdapter(dashboardstats, context))
                }
            })
        }
        expandablelvrequests.setOnGroupClickListener { p0 , _, p2, _ ->
            if (p0 != null) {
                setListViewHeight(p0, p2)
            }
            if (p0.expandableListAdapter.getChildrenCount(p2) == 0) {
                Snackbar.make(homeContainer, p0.expandableListAdapter.getGroup(p2) as String + " empty" , Snackbar.LENGTH_SHORT).show()
            }
            false

        }

        fetchNewest()
    }
    private fun setListViewHeight(listView: ExpandableListView,
                                  group: Int) {
        val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width,
                View.MeasureSpec.EXACTLY)
        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, listView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += groupItem.measuredHeight
            if (listView.isGroupExpanded(i) && i != group
                    || !listView.isGroupExpanded(i) && i == group) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(i, j, false, null,
                            listView)
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                    totalHeight += listItem.measuredHeight
                }
            }
        }
        val params = listView.layoutParams
        var height = (totalHeight
                + listView.dividerHeight * (listAdapter.groupCount - 1))
        if (height < 10) height = 200
        params.height = height
        listView.layoutParams = params
        listView.requestLayout()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
                fetchNewest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchNewest()  {
        srlHome.isRefreshing = true
        homeViewModel.getHomeStats()
        homeViewModel.getDashboardResponse()
    }
}

