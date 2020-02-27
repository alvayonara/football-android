package com.alvayonara.kade_submission_alvayonara.ui.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.StandingAdapter
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Standing
import com.alvayonara.kade_submission_alvayonara.presenter.StandingPresenter
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueMatchFragment.Companion.EXTRA_ID_LEAGUE
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.StandingView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league_standing.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.onRefresh

class LeagueStandingFragment : Fragment(), StandingView {

    private val standings: MutableList<Standing> = mutableListOf()
    private lateinit var adapter: StandingAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = act.findViewById(R.id.progress_bar_standing)
        swipeRefresh = act.findViewById(R.id.swipe_refresh_standing)

        val leagueId = arguments?.getString(EXTRA_ID_LEAGUE)

        initView()
        initData(leagueId)
    }

    private fun initView() {
        standing_list.layoutManager = LinearLayoutManager(act)
        adapter = StandingAdapter(
            act,
            standings
        )
        standing_list.adapter = adapter
    }

    private fun initData(leagueId: String?) {
        val request = ApiRepository()
        val gson = Gson()
        val presenter = StandingPresenter(
            this, request, gson
        )
        presenter.getStandingList(leagueId)

        swipeRefresh.onRefresh {
            presenter.getStandingList(leagueId)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showStandingList(data: List<Standing>) {
        swipeRefresh.isRefreshing = false
        standings.clear()
        standings.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
