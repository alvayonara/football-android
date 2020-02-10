package com.alvayonara.kade_submission_alvayonara

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String = URL(url).readText()
}