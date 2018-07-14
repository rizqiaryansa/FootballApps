package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.TestContextProvider
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.Team
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.view.fragment.teams.TeamsView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {

    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun testGetTeams() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val paramEvent = "Spanish"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListTeamByLeague(paramEvent)),
                TeamResponse::class.java)).thenReturn(response)

        presenter.getTeamList(paramEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(teams)
        Mockito.verify(view).hideLoading()
    }
}