package com.alvayonara.kade_submission_alvayonara.ui.league

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.LeagueSectionPageAdapter
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.ui.searchevent.SearchEventActivity
import com.alvayonara.kade_submission_alvayonara.ui.searchteam.SearchTeamActivity
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        initToolbar()

        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League

        initView(league)
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "League Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
    }

    private fun initView(league: League) {
        name_league_detail.text = league.name
        img_league_detail.imageResource = league.image

        // init ViewPager TabLayout
        val sectionPageAdapter =
            LeagueSectionPageAdapter(
                this,
                league,
                supportFragmentManager
            )
        view_pager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.league_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.search_match_option -> {
                startActivity<SearchEventActivity>()
                return true
            }
            R.id.search_team_option -> {
                startActivity<SearchTeamActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

