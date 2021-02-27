package com.alvayonara.kade_submission_alvayonara.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.DateTimeConvert
import com.alvayonara.kade_submission_alvayonara.model.Match

class MatchAdapter(
    private val context: Context,
    private val matches: List<Match>,
    private val typeView: Int,
    private val listener: (Match) -> Unit
) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    companion object {
        const val TYPE_LIST_VERTICAL = 1
        const val TYPE_LIST_HORIZONTAL = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MatchViewHolder {
        return when (typeView) {
            TYPE_LIST_VERTICAL -> MatchViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_vertical_match,
                    parent,
                    false
                )
            )
            TYPE_LIST_HORIZONTAL -> MatchViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_horizontal_match,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) =
        holder.bindItem(matches[position], listener)

    class MatchViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val dateTimeEvent = view.findViewById<TextView>(R.id.date_time_event)
        private val nameHomeTeam = view.findViewById<TextView>(R.id.name_home_team)
        private val nameAwayTeam = view.findViewById<TextView>(R.id.name_away_team)
        private val scoreHomeTeam = view.findViewById<TextView>(R.id.score_home_team)
        private val scoreAwayTeam = view.findViewById<TextView>(R.id.score_away_team)
        private val nameLeague = view.findViewById<TextView>(R.id.name_league)

        fun bindItem(matches: Match, listener: (Match) -> Unit) {
            dateTimeEvent.text =
                DateTimeConvert.convertTimeZone(matches.eventDate, matches.timeEvent)
            nameHomeTeam.text = matches.homeTeamName
            nameAwayTeam.text = matches.awayTeamName
            scoreHomeTeam.text = matches.homeScore
            scoreAwayTeam.text = matches.awayScore
            nameLeague.text = matches.leagueName
            itemView.setOnClickListener {
                listener(matches)
            }
        }
    }
}