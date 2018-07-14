package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.TestContextProvider
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.Event
import com.aryansa.rizqi.footballapps.model.EventResponse
import com.aryansa.rizqi.footballapps.model.Team
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.view.activity.detail.matches.DetailMatchesView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailEventPresenterTest {
    @Mock
    private lateinit var view: DetailMatchesView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailMatchesPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchesPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val home: MutableList<Team> = mutableListOf()
        val away: MutableList<Team> = mutableListOf()
        val response = EventResponse(events, events)
        val homeResponse = TeamResponse(home)
        val awayResponse = TeamResponse(away)
        val idEvent = "526916"
        val idHomeTeam = "134778"
        val idAwayTeam = "133613"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                EventResponse::class.java)).thenReturn(response)
        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                TeamResponse::class.java)).thenReturn(homeResponse)
        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                TeamResponse::class.java)).thenReturn(awayResponse)

        presenter.getEventDetail(idEvent, idHomeTeam, idAwayTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events, home, away)
        Mockito.verify(view).hideLoading()
    }
}