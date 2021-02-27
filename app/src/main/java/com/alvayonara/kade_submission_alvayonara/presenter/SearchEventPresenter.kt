package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.coroutines.CoroutineContextProvider
import com.alvayonara.kade_submission_alvayonara.response.SearchEventResponse
import com.alvayonara.kade_submission_alvayonara.view.SearchEventView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchEventPresenter(
    private val view: SearchEventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchEventList(event: String?) {
        view.hideSearch()
        view.hideSearchResult()
        view.hideSearchNotFound()
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getSearchEvent(
                            event
                        )
                    ).await(),
                SearchEventResponse::class.java
            )

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