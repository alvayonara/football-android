package com.alvayonara.kade_submission_alvayonara.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.Standing

class StandingAdapter(
    private val context: Context,
    private val standings: List<Standing>
) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StandingViewHolder = StandingViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_row_standing,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) =
        holder.bindItem(standings[position])

    class StandingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName = view.findViewById<TextView>(R.id.txt_team_name)
        private val teamPlayed = view.findViewById<TextView>(R.id.txt_team_played)
        private val teamWin = view.findViewById<TextView>(R.id.txt_team_win)
        private val teamDraw = view.findViewById<TextView>(R.id.txt_team_draw)
        private val teamLose = view.findViewById<TextView>(R.id.txt_team_lose)
        private val teamGoalsFor = view.findViewById<TextView>(R.id.txt_team_goals_for)
        private val teamGoalsAgainst = view.findViewById<TextView>(R.id.txt_team_goals_against)
        private val teamGoalsDifference =
            view.findViewById<TextView>(R.id.txt_team_goals_difference)
        private val teamTotal = view.findViewById<TextView>(R.id.txt_team_total)

        fun bindItem(standings: Standing) {
            teamName.text = standings.teamName
            teamPlayed.text = standings.teamPlayed
            teamWin.text = standings.teamWin
            teamDraw.text = standings.teamDraw
            teamLose.text = standings.teamLoss
            teamGoalsFor.text = standings.teamGoalsFor
            teamGoalsAgainst.text = standings.teamGoalsAgainst
            teamGoalsDifference.text = standings.teamGoalsDifference
            teamTotal.text = standings.teamTotal
        }
    }
}