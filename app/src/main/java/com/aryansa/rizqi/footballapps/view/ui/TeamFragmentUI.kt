package com.aryansa.rizqi.footballapps.view.ui

import android.support.design.R.attr.*
import android.support.design.R.string.appbar_scrolling_view_behavior
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.view.fragment.teams.TeamsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.viewPager

class TeamFragmentUI : AnkoComponent<TeamsFragment> {

    override fun createView(ui: AnkoContext<TeamsFragment>) = with(ui) {
        coordinatorLayout {
            lparams(width = matchParent, height =  matchParent)

            appBarLayout {
                lparams(width = matchParent, height = wrapContent)

                toolbar {
                    lparams(width = matchParent, height = actionBarSize)
                    id = R.id.toolbar_teams
                    backgroundColor = colorPrimary
                }

                tabLayout {
                    lparams(width = matchParent, height = wrapContent)
                    themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark)
                    id = R.id.tabs_teams
                    tabGravity = Gravity.FILL
                    tabMode = TabLayout.MODE_FIXED
                }
            }
            viewPager {
                id = R.id.pager_teams
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}