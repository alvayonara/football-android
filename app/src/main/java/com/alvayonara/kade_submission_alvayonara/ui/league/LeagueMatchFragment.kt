package com.alvayonara.kade_submission_alvayonara.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.MatchAdapter
import com.alvayonara.kade_submission_alvayonara.adapter.MatchAdapter.Companion.TYPE_LIST_HORIZONTAL
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.MatchPresenter
import com.alvayonara.kade_submission_alvayonara.ui.match.MatchActivity
import com.alvayonara.kade_submission_alvayonara.ui.match.MatchActivity.Companion.EXTRA_MATCH_DETAIL
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.onRefresh

class LeagueMatchFragment : Fragment(), MatchView {

    private val lastMatches: MutableList<Match> = mutableListOf()
    private val nextMatches: MutableList<Match> = mutableListOf()
    private lateinit var lastMatchAdapter: MatchAdapter
    private lateinit var nextMatchAdapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    companion object {
        const val EXTRA_ID_LEAGUE = "extra_id_league"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = act.findViewById(R.id.progress_bar)
        swipeRefresh = act.findViewById(R.id.swipe_refresh)

        val leagueId = arguments?.getString(EXTRA_ID_LEAGUE)

        initView()
        initData(leagueId)
    }

    private fun initView() {
        // recycler view last match
        last_match_list.layoutManager =
            LinearLayoutManager(act, LinearLayoutManager.HORIZONTAL, false)
        lastMatchAdapter =
            MatchAdapter(
                act,
                lastMatches,
                TYPE_LIST_HORIZONTAL
            ) {
                act.startActivity<MatchActivity>(EXTRA_MATCH_DETAIL to it)
            }
        last_match_list.adapter = lastMatchAdapter

        // recycler view next match
        next_match_list.layoutManager =
            LinearLayoutManager(act, LinearLayoutManager.HORIZONTAL, false)
        nextMatchAdapter = MatchAdapter(
            act,
            nextMatches,
            TYPE_LIST_HORIZONTAL
        ) {
            act.startActivity<MatchActivity>(EXTRA_MATCH_DETAIL to it)
        }
        next_match_list.adapter = nextMatchAdapter
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
        presenter.getMatchList(leagueId)

        swipeRefresh.onRefresh {
            presenter.getMatchList(leagueId)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(dataLastMatch: List<Match>, dataNextMatch: List<Match>) {
        swipeRefresh.isRefreshing = false

        lastMatches.clear()
        nextMatches.clear()

        lastMatches.addAll(dataLastMatch)
        nextMatches.addAll(dataNextMatch)

        lastMatchAdapter.notifyDataSetChanged()
        nextMatchAdapter.notifyDataSetChanged()
    }
}
