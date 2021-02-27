package com.alvayonara.kade_submission_alvayonara.response

import com.alvayonara.kade_submission_alvayonara.model.Match

data class SearchEventResponse(
    val event: List<Match>
)