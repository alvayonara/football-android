package com.alvayonara.kade_submission_alvayonara.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.squareup.picasso.Picasso

class TeamAdapter(
    private val context: Context,
    private val teams: List<Team>,
    private val listener: (Team) -> Unit
) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
        TeamViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row_team,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bindItem(teams[position], listener)

    class TeamViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val nameTeam = view.findViewById<TextView>(R.id.txt_team)
        private val imageTeam = view.findViewById<ImageView>(R.id.img_team)

        fun bindItem(teams: Team, listener: (Team) -> Unit) {
            nameTeam.text = teams.teamName
            teams.badgeTeam.let { Picasso.get().load(it).fit().into(imageTeam) }
            itemView.setOnClickListener {
                listener(teams)
            }
        }
    }
}