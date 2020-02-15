package com.alvayonara.kade_submission_alvayonara.ui.match

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alvayonara.kade_submission_alvayonara.DateTimeConvert
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.presenter.TeamPresenter
import com.alvayonara.kade_submission_alvayonara.utils.invisible
import com.alvayonara.kade_submission_alvayonara.utils.visible
import com.alvayonara.kade_submission_alvayonara.view.TeamView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.item_row_match.name_away_team
import kotlinx.android.synthetic.main.item_row_match.name_home_team
import kotlinx.android.synthetic.main.item_row_match.name_league
import kotlinx.android.synthetic.main.item_row_match.score_away_team
import kotlinx.android.synthetic.main.item_row_match.score_home_team

class MatchDetailActivity : AppCompatActivity(),
    TeamView {

    private lateinit var progressBar: ProgressBar
    private lateinit var match: Match
    private lateinit var presenter: TeamPresenter

    private var menuItem: Menu? = null
    private var isFavoriteMatch: Boolean = false

    companion object {
        const val EXTRA_MATCH_DETAIL = "extra_match_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        initToolbar()

        progressBar = findViewById(R.id.progress_bar)

        match = intent.getParcelableExtra(EXTRA_MATCH_DETAIL) as Match

        initView(match)
        initData(match)
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set status bar color to white
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor =
            ContextCompat.getColor(this, R.color.colorPrimary)
    }

    private fun initView(match: Match) {
        date_time_event.text = DateTimeConvert.convertTimeZone(match.eventDate, match.timeEvent)
        name_home_team.text = match.homeTeamName
        name_away_team.text = match.awayTeamName
        score_home_team.text = match.homeScore
        score_away_team.text = match.awayScore
        name_league.text = match.leagueName
        home_goal_score.text = match.homeGoalDetail
        away_goal_score.text = match.awayGoalDetail
        home_red_card.text = match.homeRedCard
        away_red_card.text = match.awayRedCard
        home_yellow_card.text = match.homeYellowCard
        away_yellow_card.text = match.awayYellowCard
        home_GK.text = match.homeGK
        away_GK.text = match.awayGK
        home_defense.text = match.homeDefense
        away_defense.text = match.awayDefense
        home_midfield.text = match.homeMidfield
        away_midfield.text = match.awayMidfield
        home_forward.text = match.homeForward
        away_forward.text = match.awayForward
        home_substitutes.text = match.homeSubtitute
        away_substitutes.text = match.awaySubstitute
    }

    private fun initData(match: Match) {
        val request =
            ApiRepository()
        val gson = Gson()
        presenter =
            TeamPresenter(
                this,
                request,
                gson
            )

        // check state favorite match from db
        isFavoriteMatch = presenter.checkStateFavoriteMatch(this, match)

        // get data team details
        presenter.getTeamDetailData(match.homeTeamId, match.awayTeamId)
    }

    private fun setFavoriteMatch() {
        if (isFavoriteMatch)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavoriteMatch()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavoriteMatch) presenter.removeFromFavoriteMatch(
                    this,
                    match
                ) else presenter.addToFavoriteMatch(this, match)

                isFavoriteMatch = !isFavoriteMatch
                setFavoriteMatch()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(dataHomeTeam: List<Team>, dataAwayTeam: List<Team>) {
        val imageHomeTeam = findViewById<ImageView>(R.id.img_home_team)
        val imageAwayTeam = findViewById<ImageView>(R.id.img_away_team)

        Picasso.get().load(dataHomeTeam[0].badgeTeam).into(imageHomeTeam)
        Picasso.get().load(dataAwayTeam[0].badgeTeam).into(imageAwayTeam)
    }
}
