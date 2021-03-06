package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.TestContextProvider
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.Event
import com.aryansa.rizqi.footballapps.model.EventResponse
import com.aryansa.rizqi.footballapps.view.fragment.matches.past.PastView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PastEventPresenterTest {
    @Mock
    private lateinit var view: PastView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: PastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PastPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetPrevEvent() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events, events)
        val paramEvent = "eventspastleague"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(paramEvent)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEventList(paramEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events)
        Mockito.verify(view).hideLoading()
    }
}