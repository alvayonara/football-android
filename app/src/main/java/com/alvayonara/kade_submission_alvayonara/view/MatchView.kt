package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(dataLastMatch: List<Match>, dataNextMatch: List<Match>)
}