package com.aryansa.rizqi.footballapps.view.ui

import android.support.v7.widget.LinearLayoutManager
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.util.SpaceItemDecoration
import com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteMatches.FavoriteMatchesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesUI : AnkoComponent<FavoriteMatchesFragment> {

    override fun createView(ui: AnkoContext<FavoriteMatchesFragment>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            swipeRefreshLayout {
                id = R.id.swipeRefreshFavoMatch
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    id = R.id.rvFavoMatch
                    layoutManager = LinearLayoutManager(ctx)
                    addItemDecoration(SpaceItemDecoration(8))
                }
            }

            progressBar {
                id = R.id.pbFavoMatches
            }.lparams {
                centerHorizontally()
            }
        }
    }
}