package com.alvayonara.kade_submission_alvayonara.ui.match


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alvayonara.kade_submission_alvayonara.*
import com.alvayonara.kade_submission_alvayonara.adapter.MatchAdapter
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.MatchPresenter
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.MatchView
import com.google.gson.Gson

import kotlinx.android.synthetic.main.fragment_match_list.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.onRefresh

class NextMatchFragment : Fragment(),
    MatchView {

    private val matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)

        val leagueId = arguments?.getString(LastMatchFragment.EXTRA_ID_LEAGUE)

        initView()
        initData(leagueId)
    }

    private fun initView() {
        match_list.layoutManager = LinearLayoutManager(act)
        adapter =
            MatchAdapter(
                act,
                matches
            ) {
                act.startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_MATCH_DETAIL to it)
            }
        match_list.adapter = adapter
    }

    private fun initData(leagueId: String?) {
        val request =
            ApiRepository()
        val gson = Gson()
        val presenter =
            MatchPresenter(
                this,
                request,
                gson
            )
        presenter.getNextMatchList(leagueId)

        swipeRefresh.onRefresh {
            presenter.getNextMatchList(leagueId)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }
}