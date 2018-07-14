package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.util.CoroutineContextProvider
import com.aryansa.rizqi.footballapps.view.fragment.teams.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter (private val view: TeamsView,
                     private val apiRequest: ApiRequest,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListTeamByLeague(match)),
                        TeamResponse::class.java
                )
            }
            view.showEventList(data.await().team)
            view.hideLoading()
        }
    }

    fun getSearchTeam(team: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSearchTeamByName(team)),
                        TeamResponse::class.java
                )
            }
            view.showEventList(data.await().team)
            view.hideLoading()
        }
    }
}