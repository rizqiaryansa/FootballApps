package com.aryansa.rizqi.footballapps.view.activity.detail.teams

import com.aryansa.rizqi.footballapps.model.Player
import com.aryansa.rizqi.footballapps.model.Team

interface DetailTeamsView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Team>, player: List<Player>)
}