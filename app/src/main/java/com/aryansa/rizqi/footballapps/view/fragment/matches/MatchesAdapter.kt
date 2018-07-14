package com.aryansa.rizqi.footballapps.view.fragment.matches

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aryansa.rizqi.footballapps.model.Event
import com.aryansa.rizqi.footballapps.view.ui.ItemMatchesUI
import com.aryansa.rizqi.footballapps.view.vh.MatchesViewHolder
import org.jetbrains.anko.AnkoContext

class MatchesAdapter(private val matchs: List<Event>) :
        RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(ItemMatchesUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindItem(matchs[position])
    }

}