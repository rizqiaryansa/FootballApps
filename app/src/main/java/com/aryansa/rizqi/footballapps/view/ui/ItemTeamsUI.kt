package com.aryansa.rizqi.footballapps.view.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aryansa.rizqi.footballapps.R
import org.jetbrains.anko.*

class ItemTeamsUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams {
                height = dip(50)
                width = dip(50)
            }

            textView {
                id = R.id.team_name
                textSize = 16f
            }.lparams {
                margin = dip(15)
            }
        }
    }
}