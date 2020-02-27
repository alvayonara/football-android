package com.alvayonara.kade_submission_alvayonara.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.alvayonara.kade_submission_alvayonara.db.database
import com.alvayonara.kade_submission_alvayonara.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailPresenter {
    fun addToFavoriteTeam(context: Context, team: Team) {
        try {
            context.database.use {
                insert(
                    Team.TABLE_FAVORITE_TEAM,
                    Team.TEAM_ID to team.teamId,
                    Team.TEAM_BADGE to team.badgeTeam,
                    Team.TEAM_NAME to team.teamName,
                    Team.STADIUM_NAME to team.stadiumName,
                    Team.TEAM_DESCRIPTION to team.descriptionTeam,
                    Team.TEAM_YEAR_FORMED to team.yearFormed,
                    Team.SPORT_NAME to team.sportName
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error add", e.message.toString())
        }
    }

    fun removeFromFavoriteTeam(context: Context, team: Team) {
        try {
            context.database.use {
                delete(
                    Team.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to team.teamId.toString()
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error remove", e.message.toString())
        }
    }

    fun checkStateFavoriteTeam(context: Context, team: Team): Boolean {
        var isFavoriteTeam = false

        context.database.use {
            val result = select(Team.TABLE_FAVORITE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to team.teamId.toString()
                )
            val favorite = result.parseList(classParser<Team>())
            if (favorite.isNotEmpty()) isFavoriteTeam = true
        }
        return isFavoriteTeam
    }
}