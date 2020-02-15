package com.alvayonara.kade_submission_alvayonara.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val id: Long?,
    @SerializedName("idEvent")
    var eventId: String? = null,
    @SerializedName("strSport")
    var sportName: String? = null,
    @SerializedName("dateEvent")
    var eventDate: String? = null,
    @SerializedName("strTime")
    var timeEvent: String? = null,
    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,
    @SerializedName("strHomeTeam")
    var homeTeamName: String? = null,
    @SerializedName("intHomeScore")
    var homeScore: String? = null,
    @SerializedName("strHomeGoalDetails")
    var homeGoalDetail: String? = null,
    @SerializedName("strHomeRedCards")
    var homeRedCard: String? = null,
    @SerializedName("strHomeYellowCards")
    var homeYellowCard: String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    var homeGK: String? = null,
    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,
    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String? = null,
    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    var homeSubtitute: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,
    @SerializedName("strAwayTeam")
    var awayTeamName: String? = null,
    @SerializedName("intAwayScore")
    var awayScore: String? = null,
    @SerializedName("strAwayGoalDetails")
    var awayGoalDetail: String? = null,
    @SerializedName("strAwayRedCards")
    var awayRedCard: String? = null,
    @SerializedName("strAwayYellowCards")
    var awayYellowCard: String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    var awayGK: String? = null,
    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,
    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String? = null,
    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    var awaySubstitute: String? = null
) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"

        const val EVENT_ID: String = "EVENT_ID"
        const val SPORT_NAME: String = "SPORT_NAME"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val LEAGUE_NAME: String = "LEAGUE_NAME"

        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val HOME_RED_CARD: String = "HOME_RED_CARD"
        const val HOME_YELLOW_CARD: String = "HOME_YELLOW_CARD"
        const val HOME_GK: String = "HOME_GK"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val HOME_SUBSTITUTE: String = "HOME_SUBSTITUTE"

        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"
        const val AWAY_RED_CARD: String = "AWAY_RED_CARD"
        const val AWAY_YELLOW_CARD: String = "AWAY_YELLOW_CARD"
        const val AWAY_GK: String = "AWAY_GK"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
        const val AWAY_SUBSTITUTE: String = "AWAY_SUBSTITUTE"
    }
}