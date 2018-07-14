package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.TestContextProvider
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.Player
import com.aryansa.rizqi.footballapps.model.PlayerResponse
import com.aryansa.rizqi.footballapps.model.Team
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailTeamsView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailTeamsPresenterTest {

    @Mock
    private lateinit var view: DetailTeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailTeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamsPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun getTestDetailTeams() {
        val detailTeam: MutableList<Team> = mutableListOf()
        val listPlayer: MutableList<Player> = mutableListOf()

        val response = TeamResponse(detailTeam)
        val homeResponse = PlayerResponse(listPlayer, listPlayer)

        val idTeam = "133604"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListDetailTeam(idTeam)),
                TeamResponse::class.java)).thenReturn(response)
        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListPlayerTeam(idTeam)),
                PlayerResponse::class.java)).thenReturn(homeResponse)

        presenter.getTeamDetail(idTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(detailTeam, listPlayer)
        Mockito.verify(view).hideLoading()
    }
}