package com.aryansa.rizqi.footballapps.view.fragment.favorites.favoriteMatches

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aryansa.rizqi.footballapps.db.MatchesDB
import com.aryansa.rizqi.footballapps.view.ui.ItemMatchesUI
import com.aryansa.rizqi.footballapps.view.vh.MatchesViewHolder
import org.jetbrains.anko.AnkoContext

class FavoriteMatchesAdapter(private val favoriteMatches: List<MatchesDB>,
                       private val listener: (MatchesDB) -> Unit):
        RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(ItemMatchesUI().createView(AnkoContext.
                create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bindFavorite(favoriteMatches[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatches.size
}