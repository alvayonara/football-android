package com.alvayonara.kade_submission_alvayonara.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.TeamAdapter
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.presenter.FavoriteTeamPresenter
import com.alvayonara.kade_submission_alvayonara.ui.team.TeamActivity
import com.alvayonara.kade_submission_alvayonara.ui.team.TeamActivity.Companion.EXTRA_TEAM_DETAIL
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.FavoriteTeamView
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    private val favorites: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutFavorite: LinearLayout
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
    }

    private fun initView() {
        progressBar = act.findViewById(R.id.progress_bar_favorite_team)
        layoutFavorite = act.findViewById(R.id.layout_favorite_team)
        swipeRefresh = act.findViewById(R.id.swipe_refresh_favorite_team)

        favorite_team_list.layoutManager = LinearLayoutManager(act)
        adapter = TeamAdapter(
            act,
            favorites
        ) {
            act.startActivity<TeamActivity>(EXTRA_TEAM_DETAIL to it)
        }
        favorite_team_list.adapter = adapter
    }

    private fun initData() {
        presenter = FavoriteTeamPresenter(this)
        presenter.getFavoriteTeamList(act)

        swipeRefresh.onRefresh {
            presenter.getFavoriteTeamList(act)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showFavorite() {
        layoutFavorite.visible()
    }

    override fun hideFavorite() {
        layoutFavorite.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        favorites.clear()
        favorites.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTeamList(act)
    }
}
