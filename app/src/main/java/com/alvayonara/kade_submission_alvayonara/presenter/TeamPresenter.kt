package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.coroutines.CoroutineContextProvider
import com.alvayonara.kade_submission_alvayonara.response.TeamResponse
import com.alvayonara.kade_submission_alvayonara.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamData(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val dataTeam = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeam(
                            leagueId
                        )
                    ).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeam(dataTeam.teams)
        }
    }
}