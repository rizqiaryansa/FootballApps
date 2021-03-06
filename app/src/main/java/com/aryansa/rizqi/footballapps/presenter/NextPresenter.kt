package com.aryansa.rizqi.footballapps.presenter

import com.aryansa.rizqi.footballapps.api.ApiRequest
import com.aryansa.rizqi.footballapps.api.TheSportDBApi
import com.aryansa.rizqi.footballapps.model.EventResponse
import com.aryansa.rizqi.footballapps.util.CoroutineContextProvider
import com.aryansa.rizqi.footballapps.view.fragment.matches.next.NextView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextPresenter (private val view: NextView,
                     private val apiRequest: ApiRequest,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSchedule(match)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().event)
            view.hideLoading()
        }
    }

    fun getSearchEventList(match: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getSearchEventByName(match)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().searchEvent)
            view.hideLoading()
        }
    }

    fun getEventListByLeague(nameLeague: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRequest.doRequest(TheSportDBApi.getListEventByLeague(nameLeague)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().searchEvent)
            view.hideLoading()
        }
    }
}