package com.aryansa.rizqi.footballapps.view.activity.detail.teams

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aryansa.rizqi.footballapps.model.Player
import com.aryansa.rizqi.footballapps.view.ui.ItemListPlayerUI
import com.aryansa.rizqi.footballapps.view.vh.ListPlayerViewHolder
import org.jetbrains.anko.AnkoContext

class DetailTeamsAdapter(private val players: List<Player>) :
        RecyclerView.Adapter<ListPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlayerViewHolder {
        return ListPlayerViewHolder(ItemListPlayerUI().createView(AnkoContext.create(parent.context,
                parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ListPlayerViewHolder, position: Int) {
        holder.bindItem(players[position])
    }
}