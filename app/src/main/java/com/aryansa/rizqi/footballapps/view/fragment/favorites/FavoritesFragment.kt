package com.aryansa.rizqi.footballapps.view.fragment.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.view.ViewPagerAdapter
import com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteMatches.FavoriteMatchesFragment
import com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteTeams.FavoriteTeamsFragment

class FavoritesFragment : Fragment() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(mToolbar)

        mToolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))

        mTabLayout.setTabTextColors(resources.getColorStateList(R.color.colorWhite))
        mTabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.colorWhite))
        setupViewPager(mViewPager)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.view_pager_layout, container, false)

        mTabLayout = v.findViewById(R.id.tabs_layout)
        mToolbar = v.findViewById(R.id.toolbar_layout)
        mViewPager = v.findViewById(R.id.pager_layout)

        return v
    }

    companion object {
        fun favoritesInstance() : FavoritesFragment= FavoritesFragment()
    }

    private fun setupViewPager(pager: ViewPager) {
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }

        val matches = FavoriteMatchesFragment.favoriteMatchesInstance()
        adapter?.addFragment(matches, getString(R.string.matches))

        val teams = FavoriteTeamsFragment.favoriteTeamsInstance()
        adapter?.addFragment(teams, getString(R.string.teams))

        pager?.adapter = adapter
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.clear()
        super.onPrepareOptionsMenu(menu)
    }
}
