package com.alvayonara.kade_submission_alvayonara.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    var badgeTeam: String? = null
)