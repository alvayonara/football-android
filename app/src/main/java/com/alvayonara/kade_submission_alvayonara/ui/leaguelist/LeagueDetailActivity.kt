package com.alvayonara.kade_submission_alvayonara.ui.leaguelist

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.League
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.imageResource

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
        supportActionBar?.title = "League Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
    }

    private fun initView(league: League) {
        name_league_detail.text = league.name
        description_league_detail.text = league.description
        img_league_detail.imageResource = league.image
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
}

