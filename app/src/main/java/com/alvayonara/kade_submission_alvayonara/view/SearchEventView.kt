package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Match

interface SearchEventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Match>)
}