package com.alvayonara.kade_submission_alvayonara.ui.searchevent

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.MatchAdapter
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.SearchEventPresenter
import com.alvayonara.kade_submission_alvayonara.ui.match.MatchDetailActivity
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.SearchEventView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class SearchEventActivity : AppCompatActivity(), SearchEventView {

    private val events: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchEventPresenter
    private lateinit var layoutSearch: LinearLayout
    private lateinit var layoutSearchResult: LinearLayout
    private lateinit var layoutSearchNotFound: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        progressBar = findViewById(R.id.progress_bar)
        layoutSearch = findViewById(R.id.layout_search)
        layoutSearchResult = findViewById(R.id.layout_search_result)
        layoutSearchNotFound = findViewById(R.id.layout_search_not_found)

        initToolbar()
        initView()
        initData()
    }

    private fun initToolbar() {
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        event_list.layoutManager = LinearLayoutManager(this)
        adapter =
            MatchAdapter(
                this,
                events
            ) {
                startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_MATCH_DETAIL to it)
            }
        event_list.adapter = adapter
    }

    private fun initData() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchEventPresenter(
            this, request, gson
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        initSearchView(searchView)

        return true
    }

    private fun initSearchView(searchView: SearchView) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getSearchEventList(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearch() {
        layoutSearch.visible()
    }

    override fun hideSearch() {
        layoutSearch.invisible()
    }

    override fun showSearchResult() {
        layoutSearchResult.visible()
    }

    override fun hideSearchResult() {
        layoutSearchResult.invisible()
    }

    override fun showSearchNotFound() {
        layoutSearchNotFound.visible()
    }

    override fun hideSearchNotFound() {
        layoutSearchNotFound.invisible()
    }

    override fun showEventList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
