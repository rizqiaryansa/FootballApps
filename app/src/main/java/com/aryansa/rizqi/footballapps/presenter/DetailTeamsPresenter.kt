package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.PlayerResponse
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.util.CoroutineContextProvider
import com.aryansa.rizqi.footballapps.view.activity.detail.teams.DetailTeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamsPresenter(private val view: DetailTeamsView,
                           private val apiRequest: ApiRequest,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(idTeam: String?) {
        view.showLoading()

        async(context.main) {
            val eventDetailTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListDetailTeam(idTeam)),
                        TeamResponse::class.java)
            }
            val listPlayerTeam = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListPlayerTeam(idTeam)),
                        PlayerResponse::class.java)
            }
            view.showEventList(eventDetailTeam.await().team, listPlayerTeam.await().player)
            view.hideLoading()
        }
    }
}