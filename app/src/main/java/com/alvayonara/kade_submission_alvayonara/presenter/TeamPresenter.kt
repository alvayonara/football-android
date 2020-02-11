package com.alvayonara.kade_submission_alvayonara.presenter

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.response.TeamResponse
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.view.TeamView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeamDetailData(teamIdHome: String?, teamIdAway: String?) {
        view.showLoading()
        doAsync {
            val dataTeamHome = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamDetail(
                            teamIdHome
                        )
                    ),
                TeamResponse::class.java
            )

            val dataTeamAway = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamDetail(
                            teamIdAway
                        )
                    ),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(dataTeamHome.teams, dataTeamAway.teams)
            }
        }
    }
}