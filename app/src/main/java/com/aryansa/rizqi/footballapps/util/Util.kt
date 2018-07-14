package com.aryansa.rizqi.footballapps.util

import android.content.Context
import android.view.View
import com.aryansa.rizqi.footballapps.db.FootballDbHelper

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

val Context.db: FootballDbHelper
    get() = FootballDbHelper.getInstance(applicationContext)