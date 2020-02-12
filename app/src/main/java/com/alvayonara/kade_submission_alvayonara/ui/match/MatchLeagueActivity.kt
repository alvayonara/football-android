package com.alvayonara.kade_submission_alvayonara.ui.match

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.SectionPageAdapter
import kotlinx.android.synthetic.main.activity_match_league.*
import org.jetbrains.anko.imageResource

class MatchLeagueActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MATCH_LEAGUE = "extra_match_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_league)

        initToolbar()

        val league = intent.getParcelableExtra(EXTRA_MATCH_LEAGUE) as League

        initView(league)
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Match List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set status bar color to white
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
    }

    private fun initView(league: League) {
        name_match_league.text = league.name
        img_match_league.imageResource = league.image

        // init ViewPager TabLayout
        val sectionPageAdapter =
            SectionPageAdapter(
                this,
                league.id,
                supportFragmentManager
            )
        view_pager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(view_pager)
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
