package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(dataHomeTeam: List<Team>, dataAwayTeam: List<Team>)
}