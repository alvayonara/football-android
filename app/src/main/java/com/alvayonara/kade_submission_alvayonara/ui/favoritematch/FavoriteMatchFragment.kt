package com.alvayonara.kade_submission_alvayonara.ui.favoritematch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.MatchAdapter
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.FavoriteMatchPresenter
import com.alvayonara.kade_submission_alvayonara.ui.match.MatchDetailActivity
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.FavoriteMatchView
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private val favorites: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutFavorite: LinearLayout
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
    }

    private fun initView() {
        progressBar = act.findViewById(R.id.progress_bar)
        layoutFavorite = act.findViewById(R.id.layout_favorite)
        swipeRefresh = act.findViewById(R.id.swipe_refresh)

        favorite_match_list.layoutManager = LinearLayoutManager(act)
        adapter = MatchAdapter(
            act,
            favorites
        ) {
            act.startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_MATCH_DETAIL to it)
        }
        favorite_match_list.adapter = adapter
    }

    private fun initData() {
        presenter = FavoriteMatchPresenter(this)
        presenter.getFavoriteMatchList(act)

        swipeRefresh.onRefresh {
            presenter.getFavoriteMatchList(act)
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

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        favorites.clear()
        favorites.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMatchList(act)
    }
}
