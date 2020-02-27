package com.alvayonara.kade_submission_alvayonara.view

import com.alvayonara.kade_submission_alvayonara.model.Standing

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun showStandingList(data: List<Standing>)
}