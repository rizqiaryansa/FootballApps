package com.aryansa.rizqi.footballapps.view.fragment.teams

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aryansa.rizqi.footballapps.model.Team
import com.aryansa.rizqi.footballapps.view.ui.ItemTeamsUI
import com.aryansa.rizqi.footballapps.view.vh.TeamViewHolder
import org.jetbrains.anko.AnkoContext

class TeamsAdapter(private val teams: List<Team>)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(ItemTeamsUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

}