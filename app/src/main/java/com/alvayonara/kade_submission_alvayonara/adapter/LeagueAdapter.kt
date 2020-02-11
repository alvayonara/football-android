package com.alvayonara.kade_submission_alvayonara.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvayonara.kade_submission_alvayonara.R
import com.alvayonara.kade_submission_alvayonara.model.League
import com.squareup.picasso.Picasso

class LeagueAdapter(
    private val context: Context,
    private val leagues: List<League>,
    private val typeView: Int,
    private val listener: (League) -> Unit
) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    companion object {
        const val TYPE_LIST = 1
        const val TYPE_GRID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LeagueViewHolder {
        return when (typeView) {
            TYPE_LIST -> LeagueViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_row_league,
                    parent,
                    false
                )
            )
            TYPE_GRID -> LeagueViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_grid_league,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class LeagueViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.name_league)
        private val image = view.findViewById<ImageView>(R.id.img_league)

        fun bindItem(leagues: League, listener: (League) -> Unit) {
            name.text = leagues.name
            leagues.image.let { Picasso.get().load(it).fit().into(image) }
            itemView.setOnClickListener {
                listener(leagues)
            }
        }
    }
}
