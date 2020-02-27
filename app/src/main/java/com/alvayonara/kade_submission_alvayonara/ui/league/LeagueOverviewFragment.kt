package com.alvayonara.kade_submission_alvayonara.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvayonara.kade_submission_alvayonara.R
import kotlinx.android.synthetic.main.fragment_league_overview.*

class LeagueOverviewFragment : Fragment() {

    companion object {
        const val EXTRA_DESCRIPTION_LEAGUE = "extra_description_league"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = arguments?.getString(EXTRA_DESCRIPTION_LEAGUE)

        description_league_detail.text = description
    }
}
