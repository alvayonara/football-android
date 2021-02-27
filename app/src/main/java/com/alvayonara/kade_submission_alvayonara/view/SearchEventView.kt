package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Match

interface SearchEventView {
    fun showLoading()
    fun hideLoading()
    fun showSearch()
    fun hideSearch()
    fun showSearchResult()
    fun hideSearchResult()
    fun showSearchNotFound()
    fun hideSearchNotFound()
    fun showEventList(data: List<Match>)
}