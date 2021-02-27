package com.alvayonara.kade_submission_alvayonara.model

import com.google.gson.annotations.SerializedName

data class Standing(

    @SerializedName("name")
    var teamName: String? = null,
    @SerializedName("teamid")
    var teamId: String? = null,
    @SerializedName("played")
    var teamPlayed: String? = null,
    @SerializedName("goalsfor")
    var teamGoalsFor: String? = null,
    @SerializedName("goalsagainst")
    var teamGoalsAgainst: String? = null,
    @SerializedName("goalsdifference")
    var teamGoalsDifference: String? = null,
    @SerializedName("win")
    var teamWin: String? = null,
    @SerializedName("draw")
    var teamDraw: String? = null,
    @SerializedName("loss")
    var teamLoss: String? = null,
    @SerializedName("total")
    var teamTotal: String? = null
)