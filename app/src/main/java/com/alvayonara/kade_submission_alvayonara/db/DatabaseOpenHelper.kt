package com.alvayonara.kade_submission_alvayonara.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alvayonara.kade_submission_alvayonara.model.Match

import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Match.TABLE_FAVORITE, true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.EVENT_ID to TEXT + UNIQUE,
            Match.SPORT_NAME to TEXT,
            Match.EVENT_DATE to TEXT,
            Match.EVENT_TIME to TEXT,
            Match.LEAGUE_NAME to TEXT,

            Match.HOME_TEAM_ID to TEXT,
            Match.HOME_TEAM_NAME to TEXT,
            Match.HOME_SCORE to TEXT,
            Match.HOME_GOAL_DETAIL to TEXT,
            Match.HOME_RED_CARD to TEXT,
            Match.HOME_YELLOW_CARD to TEXT,
            Match.HOME_GK to TEXT,
            Match.HOME_DEFENSE to TEXT,
            Match.HOME_MIDFIELD to TEXT,
            Match.HOME_FORWARD to TEXT,
            Match.HOME_SUBSTITUTE to TEXT,

            Match.AWAY_TEAM_ID to TEXT,
            Match.AWAY_TEAM_NAME to TEXT,
            Match.AWAY_SCORE to TEXT,
            Match.AWAY_GOAL_DETAIL to TEXT,
            Match.AWAY_RED_CARD to TEXT,
            Match.AWAY_YELLOW_CARD to TEXT,
            Match.AWAY_GK to TEXT,
            Match.AWAY_DEFENSE to TEXT,
            Match.AWAY_MIDFIELD to TEXT,
            Match.AWAY_FORWARD to TEXT,
            Match.AWAY_SUBSTITUTE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

