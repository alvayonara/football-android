package com.alvayonara.kade_submission_alvayonara.ui.match

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvayonara.kade_submission_alvayonara.R

class MatchDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MATCH_DETAIL = "extra_match_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
    }
}
