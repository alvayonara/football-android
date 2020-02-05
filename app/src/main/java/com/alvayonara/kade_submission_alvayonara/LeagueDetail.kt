package com.alvayonara.kade_submission_alvayonara

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*

class LeagueDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar!!.title = "League Detail"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League
        val leagueData = League(
            league.name,
            league.description,
            league.image
        )
        LeagueDetailUI(leagueData).setContentView(this)
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

    class LeagueDetailUI(private val league: League) : AnkoComponent<LeagueDetail> {
        override fun createView(ui: AnkoContext<LeagueDetail>) = with(ui) {
            scrollView {
                verticalLayout {
                    lparams(width = matchParent, height = matchParent)
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        imageResource = league.image
                    }.lparams {
                        height = dip(200)
                        width = dip(200)
                        gravity = Gravity.CENTER
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                    }

                    textView {
                        text = "${league.name}"
                        textSize = 24f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        gravity = Gravity.CENTER
                        bottomMargin = dip(30)
                    }

                    textView {
                        text = "${league.description}"
                        textSize = 16f
                    }.lparams {
                        gravity = Gravity.END
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                        bottomMargin = dip(16)
                    }
                }
            }
        }
    }
}

