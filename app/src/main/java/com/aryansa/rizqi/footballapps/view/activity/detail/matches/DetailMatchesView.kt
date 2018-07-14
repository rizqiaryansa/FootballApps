package com.aryansa.rizqi.footballapps.view.activity.detail.matches

import com.aryansa.rizqi.footballapps.model.Event
import com.aryansa.rizqi.footballapps.model.Team

interface DetailMatchesView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Event>, home: List<Team>, away: List<Team>)
}