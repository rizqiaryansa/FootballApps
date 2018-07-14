package com.aryansa.rizqi.footballapps.view.activity.detail.teams

import com.aryansa.rizqi.footballapps.model.Player

interface DetailPlayerView {
    fun hideLoading()
    fun showLoading()
    fun showPlayerDetail(player: List<Player>)
}