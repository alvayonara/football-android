package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Match

interface FavoriteMatchView {
    fun showLoading()
    fun hideLoading()
    fun showFavorite()
    fun hideFavorite()
    fun showMatchList(data: List<Match>)
}