package org.systers.mentorship.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.view.fragments.*

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val context = MentorshipApplication.getContext()

    private val fragments = listOf(HomeFragment.newInstance(), ProfileFragment.newInstance(), RelationPagerFragment.newInstance(), MembersFragment.newInstance(), RequestsFragment.newInstance())
    private val fragmentTitle = listOf(context.getString(R.string.fragment_title_home), context.getString(R.string.fragment_title_profile), context.getString(R.string.fragment_title_relation), context.getString(R.string.fragment_title_members), context.getString(R.string.fragment_title_requests))

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitle[position]

    override fun getCount(): Int = fragments.size

}