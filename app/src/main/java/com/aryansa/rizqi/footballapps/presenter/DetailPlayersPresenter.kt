package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.PlayerResponse
import com.aryansa.rizqi.footballapps.util.CoroutineContextProvider
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayersPresenter(private val view: DetailPlayerView,
                             private val apiRequest: ApiRequest,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerDetail(idPlayer: String?) {
        view.showLoading()

        async(context.main) {
            val detailPlayerTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailPlayerTeam(idPlayer)),
                        PlayerResponse::class.java)
            }
            view.showPlayerDetail(detailPlayerTeam.await().players)
            view.hideLoading()
        }
    }
}