package com.aryansa.rizqi.footballapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FootballDbHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,
        "FavoriteFootballApps.db", null, 1) {

    companion object {
        private var instance: FootballDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballDbHelper {
            if (instance == null) {
                instance = FootballDbHelper(ctx.applicationContext)
            }
            return instance as FootballDbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(MatchesDB.TABLE_MATCHES, true,
                MatchesDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchesDB.MATCHES_ID to TEXT + UNIQUE,
                MatchesDB.MATCHES_TIME to TEXT,
                MatchesDB.HOME_TEAM to TEXT,
                MatchesDB.HOME_SCORE to TEXT,
                MatchesDB.AWAY_TEAM to TEXT,
                MatchesDB.AWAY_SCORE to TEXT,
                MatchesDB.HOME_TEAM_ID to TEXT,
                MatchesDB.AWAY_TEAM_ID to TEXT)

        db?.createTable(TeamsDB.TABLE_TEAMS, true,
                TeamsDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamsDB.TEAMS_ID to TEXT + UNIQUE,
                TeamsDB.TEAMS_NAME to TEXT,
                TeamsDB.BADGE_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(MatchesDB.TABLE_MATCHES, true)
        db?.dropTable(TeamsDB.TEAMS_NAME, true)
    }
}