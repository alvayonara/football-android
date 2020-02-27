package com.alvayonara.kade_submission_alvayonara.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Long?,
    @SerializedName("idTeam")
    var teamId: String? = null,
    @SerializedName("strTeamBadge")
    var badgeTeam: String? = null,
    @SerializedName("strTeam")
    var teamName: String? = null,
    @SerializedName("strStadium")
    var stadiumName: String? = null,
    @SerializedName("strDescriptionEN")
    var descriptionTeam: String? = null,
    @SerializedName("intFormedYear")
    var yearFormed: String? = null,
    @SerializedName("strSport")
    var sportName: String? = null
) : Parcelable {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"

        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val STADIUM_NAME: String = "STADIUM_NAME"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_YEAR_FORMED: String = "TEAM_YEAR_FORMED"
        const val SPORT_NAME: String = "SPORT_NAME"
    }
}