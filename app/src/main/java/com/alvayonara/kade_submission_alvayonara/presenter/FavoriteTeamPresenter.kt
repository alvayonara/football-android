package com.alvayonara.kade_submission_alvayonara.presenter

import android.content.Context
import com.alvayonara.kade_submission_alvayonara.db.database
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.view.FavoriteTeamView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(
    private val view: FavoriteTeamView
) {

    fun getFavoriteTeamList(context: Context) {
        view.hideFavorite()
        view.showLoading()

        val favoriteData: MutableList<Team> = mutableListOf()

        context.database.use {
            val data = select(Team.TABLE_FAVORITE_TEAM)
            val favorite = data.parseList(classParser<Team>())

            favoriteData.addAll(favorite)
        }

        view.hideLoading()

        view.showTeamList(favoriteData)
        view.showFavorite()
    }
}