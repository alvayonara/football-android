package com.alvayonara.kade_submission_alvayonara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.kade_submission_alvayonara.LeagueDetail.Companion.EXTRA_LEAGUE
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI(leagues).setContentView(this)

        initData()
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.leauge_name)
        val description = resources.getStringArray(R.array.league_description)
        val image = resources.obtainTypedArray(R.array.leauge_image)

        leagues.clear()

        for (i in name.indices) {
            leagues.add(
                League(
                    name[i],
                    description[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        image.recycle()
    }

    class MainActivityUI(private val leagues: MutableList<League>) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorDark)

                recyclerView {
                    lparams(width = matchParent, height = matchParent)

                    layoutManager = LinearLayoutManager(ctx)
                    adapter = LeagueAdapter(leagues) {
                        val league = League(
                            "${it.name}", "${it.description}", it.image
                        )

                        // anko commons: intent builder
                        ctx.startActivity<LeagueDetail>(EXTRA_LEAGUE to league)
                    }
                }
            }
        }
    }


}
