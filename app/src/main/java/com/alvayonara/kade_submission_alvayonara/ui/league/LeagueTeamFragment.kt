package com.alvayonara.kade_submission_alvayonara.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.TeamAdapter
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.presenter.TeamPresenter
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueMatchFragment.Companion.EXTRA_ID_LEAGUE
import com.alvayonara.kade_submission_alvayonara.ui.team.TeamActivity
import com.alvayonara.kade_submission_alvayonara.ui.team.TeamActivity.Companion.EXTRA_TEAM_DETAIL
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act

class LeagueTeamFragment : Fragment(), TeamView {

    private val teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = act.findViewById(R.id.progress_bar_team)

        val leagueId = arguments?.getString(EXTRA_ID_LEAGUE)

        initView()
        initData(leagueId)
    }

    private fun initView() {
        team_list.layoutManager =
            LinearLayoutManager(act)
        adapter =
            TeamAdapter(
                act,
                teams
            ) {
                act.startActivity<TeamActivity>(EXTRA_TEAM_DETAIL to it)
            }
        team_list.adapter = adapter
    }

    private fun initData(leagueId: String?) {
        val request =
            ApiRepository()
        val gson = Gson()
        val presenter =
            TeamPresenter(
                this,
                request,
                gson
            )
        presenter.getTeamData(leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeam(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
