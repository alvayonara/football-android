package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.coroutines.CoroutineContextProvider
import com.alvayonara.kade_submission_alvayonara.response.SearchTeamResponse
import com.alvayonara.kade_submission_alvayonara.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchTeamList(team: String?) {
        view.hideSearch()
        view.hideSearchResult()
        view.hideSearchNotFound()
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getSearchTeam(
                            team
                        )
                    ).await(),
                SearchTeamResponse::class.java
            )

            view.hideLoading()

            // if search team not found
            if (data?.teams == null) {
                view.showSearchNotFound()
            } else {
                // filter data by sport name (soccer)
                view.showTeamList(data.teams.filter { it.sportName == "Soccer" })
                view.showSearchResult()
            }
        }
    }
}