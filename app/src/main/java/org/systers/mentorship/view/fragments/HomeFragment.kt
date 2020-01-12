package org.systers.mentorship.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.systers.mentorship.R
import org.systers.mentorship.databinding.FragmentHomeBinding
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.User
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.AchievementsAdapter
import org.systers.mentorship.view.adapters.HomeRequestsAdapter
import org.systers.mentorship.view.adapters.PastRelationshipsAdapter
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.HomeViewModel
import org.systers.mentorship.viewmodels.ProfileViewModel
import org.systers.mentorship.viewmodels.RelationViewModel
import org.systers.mentorship.viewmodels.TasksViewModel
import kotlin.math.roundToInt

/**
 * The fragment is responsible for showing a welcoming message and show some statistics of the User
 * usage of the app
 */
class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var relationViewModel: RelationViewModel
    private lateinit var tasksViewModel: TasksViewModel

    private lateinit var binding: FragmentHomeBinding

    private lateinit var achievementsAdapter: AchievementsAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var requestsAdapter: HomeRequestsAdapter
    private lateinit var pastRelationshipsAdapter: PastRelationshipsAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    private val requestsList = MutableList(4) { 0 }

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
        super.onCreate(savedInstanceState)

        baseActivity.showProgressDialog(getString(R.string.fetch_user_profile))

        initializeRecyclerViews()
        initializeClickListeners()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        relationViewModel = ViewModelProviders.of(this).get(RelationViewModel::class.java)
        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        profileViewModel.successfulGet.observe(this, Observer {
            if (it != null && it)
                calculateProfileData(profileViewModel.user)
            else
                Snackbar.make(binding.root, relationViewModel.message, Snackbar.LENGTH_LONG).show()
        })
        profileViewModel.getProfile()

        with(homeViewModel) {
            userStats.observe(viewLifecycleOwner, Observer { stats ->
                binding.stats = stats

                requestsList[0] = stats.pendingRequests
                requestsList[1] = stats.acceptedRequests
                requestsList[2] = stats.rejectedRequests
                requestsList[3] = stats.completedRelations
                requestsAdapter.setData(requestsList)

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
        }

        relationViewModel.successfulGet.observe(this, Observer {
            if (it != null && it)
                with(relationViewModel.mentorshipRelation) {
                    if (id != 0) {
                        tasksViewModel.getTasks(id)
                        setRelationDetails(this)
                    }
                } else
                Snackbar.make(binding.root, relationViewModel.message, Snackbar.LENGTH_LONG).show()

            baseActivity.hideProgressDialog()

            homeContainer.visibility = View.VISIBLE

            startAnimations()
        })
        relationViewModel.getCurrentRelationDetails()

        tasksViewModel.successful.observe(this, Observer {
            if (it != null && it) {
                val tasksList = tasksViewModel.tasksList

                if (tasksList.isEmpty()) {
                    tvNoTasks.visibility = View.VISIBLE
                    layoutRVTasks.visibility = View.GONE
                } else {
                    tasksAdapter = TasksAdapter(tasksViewModel.tasksList, true) {}
                    linearLayoutManager = LinearLayoutManager(context)

                    rvTasks.apply {
                        adapter = tasksAdapter
                        layoutManager = linearLayoutManager
                    }
                }
            } else
                Snackbar.make(binding.root, tasksViewModel.message, Snackbar.LENGTH_LONG).show()
        })

    }

    private fun initializeRecyclerViews() {
        requestsAdapter = HomeRequestsAdapter(requestsList)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rvRequests.apply {
            // in order to optimize performance
            setHasFixedSize(true)
            adapter = requestsAdapter
            layoutManager = linearLayoutManager
        }

        achievementsAdapter = AchievementsAdapter()
        linearLayoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, linearLayoutManager.orientation)

        rvAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(divider)
        }

        // TODO: Add past relationships functionality
        val pastRelationships = listOf<User>()

        if (pastRelationships.isNotEmpty()) {
            tvNoPastRelationships.visibility = View.GONE

            pastRelationshipsAdapter = PastRelationshipsAdapter(pastRelationships)
            linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            rvPastRelationships.apply {
                visibility = View.VISIBLE
                adapter = pastRelationshipsAdapter
                layoutManager = linearLayoutManager
            }
        }
    }

    private fun initializeClickListeners() {
        btnGetStarted.setOnClickListener {
            MainActivity.instance.showMembersFragment()
        }
        btnCompleteProfile.setOnClickListener {
            MainActivity.instance.showProfileFragment()
        }
        btnNextMeeting.setOnClickListener {
            // TODO: Add NextMeetingActivity
            Snackbar.make(homeContainer, R.string.not_implemented, Snackbar.LENGTH_SHORT).show()
        }

        btnAllTasks.setOnClickListener {
            MainActivity.instance.showRelationFragment(1)
        }
        btnAllAchievements.setOnClickListener {
            MainActivity.instance.showRelationFragment(1)
            // TODO: Add AchievementsActivity for past achievements
        }
        btnAllPastRelationships.setOnClickListener {
            // TODO: Add PastRelationshipsActivity
            Snackbar.make(homeContainer, R.string.not_implemented, Snackbar.LENGTH_SHORT).show()
        }

        btnGetStartedClose.setOnClickListener {
            // TODO: Save closed state
            layoutGetStarted.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_disappear_to_right)
                    .apply {
                        setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationRepeat(animation: Animation?) {}
                            override fun onAnimationStart(animation: Animation?) {}

                            override fun onAnimationEnd(animation: Animation?) {
                                layoutGetStarted.visibility = View.GONE
                            }
                        })
                    })

        }
        btnCompleteProfileClose.setOnClickListener {
            // TODO: Save closed state
            layoutCompleteProfile.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_disappear_to_left)
                    .apply {
                        setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationRepeat(animation: Animation?) {}
                            override fun onAnimationStart(animation: Animation?) {}

                            override fun onAnimationEnd(animation: Animation?) {
                                layoutCompleteProfile.visibility = View.GONE
                            }
                        })
                    })

        }
    }

    private fun setRelationDetails(relationship: Relationship) {
        layoutGetStarted.visibility = View.GONE
        layoutCompleteProfile.visibility = View.GONE
        rvRequests.visibility = View.GONE

        // TODO: Add nextMeeting property to Relationship class
        //  tvNextMeeting.text = relationship.nextMeeting

        layoutNextMeeting.visibility = View.VISIBLE
        tvTasksTitle.visibility = View.VISIBLE
        btnAllTasks.visibility = View.VISIBLE
        layoutRVTasks.visibility = View.VISIBLE
    }

    private fun startAnimations() {
        layoutGetStarted.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_appear_from_right))
        layoutCompleteProfile.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_appear_from_left))
    }

    private fun calculateProfileData(user: User) {
        // there are only 8 relevant fields -> 100/8 = 12.5
        var progress = 0.toDouble()

        if (!user.bio.isNullOrBlank())
            progress += 12.5
        if (!user.location.isNullOrBlank())
            progress += 12.5
        if (!user.occupation.isNullOrBlank())
            progress += 12.5
        if (!user.organization.isNullOrBlank())
            progress += 12.5
        if (!user.interests.isNullOrBlank())
            progress += 12.5
        if (!user.skills.isNullOrBlank())
            progress += 12.5
        if (!user.slackUsername.isNullOrBlank())
            progress += 12.5
        if (!user.avatar.isNullOrBlank())
            progress += 12.5

        progressCompleteProfile.progress = progress.roundToInt()
        tvProgress.text = getString(R.string.progress, progress.roundToInt())
    }

}

