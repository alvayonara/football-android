package com.alvayonara.kade_submission_alvayonara.presenter

import android.content.Context
import com.alvayonara.kade_submission_alvayonara.db.database
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.view.FavoriteMatchView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter(
    private val view: FavoriteMatchView
) {

    fun getFavoriteMatchList(context: Context) {
        view.hideFavorite()
        view.showLoading()

        val favoriteData: MutableList<Match> = mutableListOf()

        context.database.use {
            val data = select(Match.TABLE_FAVORITE)
            val favorite = data.parseList(classParser<Match>())

            favoriteData.addAll(favorite)
        }

        view.hideLoading()

        view.showMatchList(favoriteData)
        view.showFavorite()
    }
}