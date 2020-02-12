package com.alvayonara.kade_submission_alvayonara.ui.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.adapter.LeagueAdapter
import com.alvayonara.kade_submission_alvayonara.adapter.LeagueAdapter.Companion.TYPE_GRID
import com.alvayonara.kade_submission_alvayonara.model.League
import com.alvayonara.kade_submission_alvayonara.ui.match.MatchLeagueActivity.Companion.EXTRA_MATCH_LEAGUE
import com.alvayonara.kade_submission_alvayonara.ui.searchevent.SearchEventActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act

class MatchFragment : Fragment() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
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

    private fun initView() {
        league_list.layoutManager = GridLayoutManager(act, 3)
        league_list.adapter =
            LeagueAdapter(
                act,
                leagues,
                TYPE_GRID
            ) {
                act.startActivity<MatchLeagueActivity>(
                    EXTRA_MATCH_LEAGUE to it
                )
            }

        img_search.setOnClickListener {
            act.startActivity<SearchEventActivity>()
        }
    }
}
