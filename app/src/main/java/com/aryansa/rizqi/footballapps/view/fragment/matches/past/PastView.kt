package com.aryansa.rizqi.footballapps.view.fragment.matches.past

import com.aryansa.rizqi.footballapps.model.Event

interface PastView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
    fun errorMessage(message: String?)
}