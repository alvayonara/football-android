package com.alvayonara.kade_submission_alvayonara.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueMatchFragment
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueMatchFragment.Companion.EXTRA_ID_LEAGUE
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueOverviewFragment
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueOverviewFragment.Companion.EXTRA_DESCRIPTION_LEAGUE
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueStandingFragment
import com.alvayonara.kade_submission_alvayonara.ui.league.LeagueTeamFragment

class LeagueSectionPageAdapter(
    private val context: Context,
    private val league: League,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3,
        R.string.tab_text_4
    )

    override fun getItem(position: Int): Fragment {
        // pass league id to fragment (LastMatchFragment & NextMatchFragment)
        val mBundle = Bundle()
        mBundle.putString(EXTRA_ID_LEAGUE, league.id)
        mBundle.putString(EXTRA_DESCRIPTION_LEAGUE, league.description)

        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = LeagueOverviewFragment()
                fragment.arguments = mBundle
            }
            1 -> {
                fragment = LeagueMatchFragment()
                fragment.arguments = mBundle
            }
            2 -> {
                fragment = LeagueStandingFragment()
                fragment.arguments = mBundle
            }
            3 -> {
                fragment = LeagueTeamFragment()
                fragment.arguments = mBundle
            }

        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 4
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitles[position])
    }
}