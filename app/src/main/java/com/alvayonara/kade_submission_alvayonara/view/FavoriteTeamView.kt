package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Team

interface FavoriteTeamView {
    fun showLoading()
    fun hideLoading()
    fun showFavorite()
    fun hideFavorite()
    fun showTeamList(data: List<Team>)
}