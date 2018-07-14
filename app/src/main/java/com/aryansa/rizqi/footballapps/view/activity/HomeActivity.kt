package com.aryansa.rizqi.footballapps.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.Menu
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.view.fragment.favorites.FavoritesFragment
import com.aryansa.rizqi.footballapps.view.fragment.matches.MatchesFragment
import com.aryansa.rizqi.footballapps.view.fragment.teams.TeamsFragment
import com.aryansa.rizqi.footballapps.view.ui.HomeActivityUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class HomeActivity : AppCompatActivity() {

    lateinit var toolBar: ActionBar
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HomeActivityUI().setContentView(this)

        bottomNavigation = find(R.id.navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            addFragment(MatchesFragment.matchesInstance())
        }
    }

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                val matchesFragment = MatchesFragment.matchesInstance()
                addFragment(matchesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                val teamsFragment = TeamsFragment.teamsInstance()
                addFragment(teamsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoFragment = FavoritesFragment.favoritesInstance()
                addFragment(favoFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
