package com.alvayonara.kade_submission_alvayonara

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alvayonara.kade_submission_alvayonara.ui.match.LastMatchFragment
import com.alvayonara.kade_submission_alvayonara.ui.match.LastMatchFragment.Companion.EXTRA_ID_LEAGUE
import com.alvayonara.kade_submission_alvayonara.ui.match.NextMatchFragment

class SectionPageAdapter(
    private val context: Context,
    private val leagueId: String?,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)

    override fun getItem(position: Int): Fragment {
        val mBundle = Bundle()
        mBundle.putString(EXTRA_ID_LEAGUE, leagueId)

        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = LastMatchFragment()
                fragment.arguments = mBundle
            }
            1 -> {
                fragment = NextMatchFragment()
                fragment.arguments = mBundle
            }
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}