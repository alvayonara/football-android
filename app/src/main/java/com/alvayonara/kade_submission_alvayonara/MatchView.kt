package com.alvayonara.kade_submission_alvayonara

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}