package com.aryansa.rizqi.footballapps.view.fragment.matches

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.R.color.colorWhite
import com.aryansa.rizqi.footballapps.view.ViewPagerAdapter
import com.aryansa.rizqi.footballapps.view.fragment.matches.next.NextMatchesFragment
import com.aryansa.rizqi.footballapps.view.fragment.matches.past.PastMatchesFragment

class MatchesFragment : Fragment() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(mToolbar)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.view_pager_layout, container, false)

        mTabLayout = v.findViewById(R.id.tabs_layout)
        mToolbar = v.findViewById(R.id.toolbar_layout)
        mViewPager = v.findViewById(R.id.pager_layout)

        mToolbar.setTitleTextColor(resources.getColor(colorWhite))

        mTabLayout.setTabTextColors(resources.getColorStateList(colorWhite))
        mTabLayout.setSelectedTabIndicatorColor(resources.getColor(colorWhite))
        setupViewPager(mViewPager)
        mTabLayout.setupWithViewPager(mViewPager)

        return v
    }


    companion object {
        fun matchesInstance(): MatchesFragment = MatchesFragment()
    }

    private fun setupViewPager(pager: ViewPager) {
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }

        val past = PastMatchesFragment.pastInstance()
        adapter?.addFragment(past, getString(R.string.title_past_fragment))

        val next = NextMatchesFragment.nextIntance()
        adapter?.addFragment(next, getString(R.string.title_next_fragment))

        pager?.adapter = adapter
    }
}