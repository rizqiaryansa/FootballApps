package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.TestContextProvider
import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.Player
import com.aryansa.rizqi.footballapps.model.PlayerResponse
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailPlayerView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailPlayerPresenterTest {
    @Mock
    private lateinit var view: DetailPlayerView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRequest: ApiRequest

    private lateinit var presenter: DetailPlayersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPlayersPresenter(view, apiRequest, gson, TestContextProvider())
    }

    @Test
    fun getTestDetailPlayers() {
        val detailPlayer: MutableList<Player> = mutableListOf()

        val playerResponse = PlayerResponse(detailPlayer, detailPlayer)

        val idPlayer = "34145411"

        `when`(gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailPlayerTeam(idPlayer)),
                PlayerResponse::class.java)).thenReturn(playerResponse)

        presenter.getPlayerDetail(idPlayer)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showPlayerDetail(detailPlayer)
        Mockito.verify(view).hideLoading()
    }
}