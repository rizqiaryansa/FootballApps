package com.aryansa.rizqi.footballapps.view.fragment.teams

import com.aryansa.rizqi.footballapps.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Team>?)
}