package com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteTeams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.R.string.item_teamdetail_id
import com.aryansa.rizqi.footballapps.db.TeamsDB
import com.aryansa.rizqi.footballapps.util.db
import com.aryansa.rizqi.footballapps.util.gone
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailTeamsActivity
import com.aryansa.rizqi.footballapps.view.ui.FavoriteTeamsUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamsFragment : Fragment() {

    private var favoTeams: MutableList<TeamsDB> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteTeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initId()

        adapter = FavoriteTeamsAdapter(favoTeams) {
            ctx.startActivity<DetailTeamsActivity>(getString(item_teamdetail_id) to
                "${it.teamId}")
        }

        recyclerView.adapter = adapter
        showFavorite()
        swipeRefreshLayout.onRefresh {
            favoTeams.clear()
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return FavoriteTeamsUI().createView(AnkoContext.create(ctx, this))
    }

    companion object {
        fun favoriteTeamsInstance(): FavoriteTeamsFragment = FavoriteTeamsFragment()
    }

    private fun initId() {
        swipeRefreshLayout = find(R.id.swipeRefreshFavoTeams)
        recyclerView = find(R.id.rvFavoTeams)
        progressBar = find(R.id.pbFavoTeams)
    }

    private fun showFavorite() {
        context?.db?.use {
            swipeRefreshLayout.isRefreshing = false
            progressBar.gone()
            val result = select(TeamsDB.TABLE_TEAMS)
            val favorite = result.parseList(classParser<TeamsDB>())
            favoTeams.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
