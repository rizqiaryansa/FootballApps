package com.aryansa.rizqi.footballapps.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams")
        val team: List<Team>
)