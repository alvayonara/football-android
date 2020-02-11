package com.alvayonara.kade_submission_alvayonara.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
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
) : Parcelable