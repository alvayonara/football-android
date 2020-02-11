package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(dataHomeTeam: List<Team>, dataAwayTeam: List<Team>)
}