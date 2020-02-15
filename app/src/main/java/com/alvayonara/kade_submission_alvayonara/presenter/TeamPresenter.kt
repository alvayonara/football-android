package com.alvayonara.kade_submission_alvayonara.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.api.TheSportDBApi
import com.alvayonara.kade_submission_alvayonara.db.database
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.response.TeamResponse
import com.alvayonara.kade_submission_alvayonara.view.TeamView
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeamDetailData(teamIdHome: String?, teamIdAway: String?) {
        view.showLoading()
        doAsync {
            val dataTeamHome = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamDetail(
                            teamIdHome
                        )
                    ),
                TeamResponse::class.java
            )

            val dataTeamAway = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamDetail(
                            teamIdAway
                        )
                    ),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(dataTeamHome.teams, dataTeamAway.teams)
            }
        }
    }

    fun addToFavoriteMatch(context: Context, match: Match) {
        try {
            context.database.use {
                insert(
                    Match.TABLE_FAVORITE,
                    Match.EVENT_ID to match.eventId,
                    Match.SPORT_NAME to match.sportName,
                    Match.EVENT_DATE to match.eventDate,
                    Match.EVENT_TIME to match.timeEvent,
                    Match.LEAGUE_NAME to match.leagueName,

                    Match.HOME_TEAM_ID to match.homeTeamId,
                    Match.HOME_TEAM_NAME to match.homeTeamName,
                    Match.HOME_SCORE to match.homeScore,
                    Match.HOME_GOAL_DETAIL to match.homeGoalDetail,
                    Match.HOME_RED_CARD to match.homeRedCard,
                    Match.HOME_YELLOW_CARD to match.homeYellowCard,
                    Match.HOME_GK to match.homeGK,
                    Match.HOME_DEFENSE to match.homeDefense,
                    Match.HOME_MIDFIELD to match.homeMidfield,
                    Match.HOME_FORWARD to match.homeForward,
                    Match.HOME_SUBSTITUTE to match.homeSubtitute,

                    Match.AWAY_TEAM_ID to match.awayTeamId,
                    Match.AWAY_TEAM_NAME to match.awayTeamName,
                    Match.AWAY_SCORE to match.awayScore,
                    Match.AWAY_GOAL_DETAIL to match.awayGoalDetail,
                    Match.AWAY_RED_CARD to match.awayRedCard,
                    Match.AWAY_YELLOW_CARD to match.awayYellowCard,
                    Match.AWAY_GK to match.awayGK,
                    Match.AWAY_DEFENSE to match.awayDefense,
                    Match.AWAY_MIDFIELD to match.awayMidfield,
                    Match.AWAY_FORWARD to match.awayForward,
                    Match.AWAY_SUBSTITUTE to match.awaySubstitute
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error add", e.message.toString())
        }
    }

    fun removeFromFavoriteMatch(context: Context, match: Match) {
        try {
            context.database.use {
                delete(
                    Match.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to match.eventId.toString()
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.e("error remove", e.message.toString())
        }
    }

    fun checkStateFavoriteMatch(context: Context, match: Match): Boolean {
        var isFavoriteMatch = false

        context.database.use {
            val result = select(Match.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to match.eventId.toString()
                )
            val favorite = result.parseList(classParser<Match>())
            if (favorite.isNotEmpty()) isFavoriteMatch = true
        }
        return isFavoriteMatch
    }
}