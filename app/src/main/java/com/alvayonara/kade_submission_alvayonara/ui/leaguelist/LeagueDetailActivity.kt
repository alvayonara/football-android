package com.alvayonara.kade_submission_alvayonara.ui.leaguelist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.R
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

        name_league_detail.text = league.name
        description_league_detail.text = league.description
        img_league_detail.imageResource = league.image
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "League Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set status bar color to white
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
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

