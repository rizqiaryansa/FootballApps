package com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteTeams

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aryansa.rizqi.footballapps.db.TeamsDB
import com.aryansa.rizqi.footballapps.view.ui.ItemTeamsUI
import com.aryansa.rizqi.footballapps.view.vh.TeamViewHolder
import org.jetbrains.anko.AnkoContext

class FavoriteTeamsAdapter(private val favoriteMatches: List<TeamsDB>,
                             private val listener: (TeamsDB) -> Unit):
        RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemTeamsUI().createView(AnkoContext.
                create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindFavorite(favoriteMatches[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatches.size
}