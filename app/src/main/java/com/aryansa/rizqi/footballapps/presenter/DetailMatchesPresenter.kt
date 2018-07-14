package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.EventResponse
import com.aryansa.rizqi.footballapps.model.TeamResponse
import com.aryansa.rizqi.footballapps.util.CoroutineContextProvider
import com.aryansa.rizqi.footballapps.view.activity.detail.matches.DetailMatchesView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchesPresenter(private val view: DetailMatchesView,
                             private val apiRequest: ApiRequest,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

        async(context.main) {
            val eventDetail = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                        EventResponse::class.java)
            }
            val badgeHome = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                        TeamResponse::class.java)
            }
            val badgeAway =  bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                        TeamResponse::class.java)
            }
            view.showEventList(eventDetail.await().event, badgeHome.await().team,
                    badgeAway.await().team)
            view.hideLoading()
        }
    }
}