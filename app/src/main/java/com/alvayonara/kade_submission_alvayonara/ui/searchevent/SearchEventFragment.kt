package com.alvayonara.kade_submission_alvayonara.ui.searchevent


import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
import kotlinx.android.synthetic.main.fragment_match_list.*
import kotlinx.android.synthetic.main.fragment_search_event.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

class SearchEventFragment : Fragment(), SearchEventView {

    private val events: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchEventPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        event_list.layoutManager = LinearLayoutManager(act)
        adapter =
            MatchAdapter(
                act,
                events
            ) {
                act.startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_MATCH_DETAIL to it)
            }
        event_list.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchEventPresenter(
            this, request, gson
        )
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            act.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Search"
        setHasOptionsMenu(true)

        // set status bar color to white
        act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        act.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        act.window.statusBarColor = ContextCompat.getColor(act, android.R.color.white)

        // set light status bar (android M or up)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val view = act.findViewById<View>(android.R.id.content)
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager = act.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(act.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                toast(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
