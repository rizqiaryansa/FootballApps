package com.aryansa.rizqi.footballapps.view.fragment.matches.next

import com.aryansa.rizqi.footballapps.model.Event

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
    fun errorMessage(message: String?)
}