package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.response.SearchEventResponse
import com.alvayonara.kade_submission_alvayonara.view.SearchEventView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchEventPresenter(
    private val view: SearchEventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchEventList(event: String?) {
        view.hideSearch()
        view.hideSearchResult()
        view.hideSearchNotFound()
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getSearchEvent(
                            event
                        )
                    ),
                SearchEventResponse::class.java
            )

            uiThread {
                view.hideLoading()

                // if search match (event) not found
                if (data?.event == null) {
                    view.showSearchNotFound()
                } else {
                    // filter data by sport name (soccer)
                    view.showEventList(data.event.filter { it.sportName == "Soccer" })
                    view.showSearchResult()
                }
            }
        }
    }
}