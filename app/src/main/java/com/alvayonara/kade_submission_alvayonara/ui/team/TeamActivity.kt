package com.alvayonara.kade_submission_alvayonara.ui.team

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.presenter.TeamDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {

    private lateinit var team: Team
    private lateinit var presenter: TeamDetailPresenter

    private var menuItem: Menu? = null
    private var isFavoriteTeam: Boolean = false

    companion object {
        const val EXTRA_TEAM_DETAIL = "extra_team_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        initToolbar()

        team = intent.getParcelableExtra(EXTRA_TEAM_DETAIL) as Team

        initView(team)
        initData(team)
    }

    private fun initToolbar() {
        // set Toolbar (default: NoActionBar)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
    }

    private fun initView(team: Team) {
        name_team_detail.text = team.teamName
        txt_team_formed_year.text = team.yearFormed
        txt_team_stadium.text = team.stadiumName
        txt_team_description.text = team.descriptionTeam
        Picasso.get().load(team.badgeTeam).into(img_team_detail)
    }

    private fun initData(team: Team) {
        presenter = TeamDetailPresenter()

        // check state favorite team from db
        isFavoriteTeam = presenter.checkStateFavoriteTeam(this, team)
    }

    private fun setFavoriteTeam() {
        if (isFavoriteTeam)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_detail_menu, menu)
        menuItem = menu
        setFavoriteTeam()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavoriteTeam) presenter.removeFromFavoriteTeam(
                    this,
                    team
                ) else presenter.addToFavoriteTeam(this, team)

                isFavoriteTeam = !isFavoriteTeam
                setFavoriteTeam()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
