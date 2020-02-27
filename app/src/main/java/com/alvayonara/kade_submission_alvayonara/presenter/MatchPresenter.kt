package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.coroutines.CoroutineContextProvider
import com.alvayonara.kade_submission_alvayonara.response.MatchResponse
import com.alvayonara.kade_submission_alvayonara.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val dataLastMatch = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getLastMatch(
                            league
                        )
                    ).await(),
                MatchResponse::class.java
            )

            val dataNextMatch = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getNextMatch(
                            league
                        )
                    ).await(),
                MatchResponse::class.java
            )

            view.hideLoading()
            view.showMatchList(dataLastMatch.events, dataNextMatch.events)
        }
    }
}