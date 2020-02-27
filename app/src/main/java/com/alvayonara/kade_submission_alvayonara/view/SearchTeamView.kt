package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Team

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearch()
    fun hideSearch()
    fun showSearchResult()
    fun hideSearchResult()
    fun showSearchNotFound()
    fun hideSearchNotFound()
    fun showTeamList(data: List<Team>)
}