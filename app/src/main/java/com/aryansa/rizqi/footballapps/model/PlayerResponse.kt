package com.aryansa.rizqi.footballapps.model

import com.google.gson.annotations.SerializedName


data class PlayerResponse (
        val player: List<Player>,
        @SerializedName("players")
        val players: List<Player>
)