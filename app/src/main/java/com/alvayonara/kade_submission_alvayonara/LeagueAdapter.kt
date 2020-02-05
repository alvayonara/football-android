package com.alvayonara.kade_submission_alvayonara

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LeagueAdapter(
    private val leagues: List<League>,
    private val listener: (League) -> Unit
) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder =
        LeagueViewHolder(LeagueUI().createView(AnkoContext.create(parent.context, parent)))

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

    class LeagueUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        leftMargin = dip(8)
                        rightMargin = dip(8)
                        topMargin = dip(8)
                    }

                    cardElevation = dip(4).toFloat()
                    radius = dip(8).toFloat()

                    verticalLayout {
                        orientation = LinearLayout.HORIZONTAL
                        padding = dip(8)

                        imageView {
                            id = R.id.img_league
                        }.lparams {
                            height = dip(75)
                            width = dip(75)
                        }

                        textView {
                            id = R.id.name_league
                            textSize = 16f
                        }.lparams {
                            margin = dip(16)
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                }
            }
        }
    }
}
