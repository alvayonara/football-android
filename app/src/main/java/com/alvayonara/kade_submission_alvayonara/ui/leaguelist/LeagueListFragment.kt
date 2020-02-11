package com.alvayonara.kade_submission_alvayonara.ui.leaguelist


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.adapter.LeagueAdapter
import com.alvayonara.kade_submission_alvayonara.adapter.LeagueAdapter.Companion.TYPE_LIST
import com.alvayonara.kade_submission_alvayonara.R
import kotlinx.android.synthetic.main.activity_leaguelist.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act

class LeagueListFragment : Fragment() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        initData()

        league_list.layoutManager = LinearLayoutManager(act, LinearLayoutManager.HORIZONTAL, false)
        league_list.adapter =
            LeagueAdapter(
                act,
                leagues,
                TYPE_LIST
            ) {
                act.startActivity<LeagueDetailActivity>(
                    LeagueDetailActivity.EXTRA_LEAGUE to it
                )
            }
    }

    private fun initToolbar() {
        // set status bar color to white
        act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        act.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        act.window.statusBarColor = ContextCompat.getColor(act, android.R.color.white)

        // set light status bar (android M or up)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val view = act.findViewById<View>(android.R.id.content)
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val description = resources.getStringArray(R.array.league_description)
        val image = resources.obtainTypedArray(R.array.league_image)

        leagues.clear()

        for (i in name.indices) {
            leagues.add(
                League(
                    id[i],
                    name[i],
                    description[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        image.recycle()
    }
}
