package com.aryansa.rizqi.footballapps.view.vh

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.aryansa.rizqi.footballapps.R
import com.aryansa.rizqi.footballapps.model.Player
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailPlayersActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ListPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivPlayer: ImageView = view.find(R.id.ivListPlayer)
    private val namePlayer: TextView = view.find(R.id.tvListNamePlayer)
    private val namePosition: TextView = view.find(R.id.tvPositionListPlayer)

    fun bindItem(players: Player) {
        Picasso.get().load(players.strCutout).into(ivPlayer)
        namePlayer.text = players.strPlayer
        namePosition.text = players.strPosition

        val ctx = itemView.context

        itemView.setOnClickListener {
            ctx.startActivity<DetailPlayersActivity>(
                    ctx.getString(R.string.item_teamplayer_id) to players.playerId)
        }
    }
}