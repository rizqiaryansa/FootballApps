package com.aryansa.rizqi.footballapps.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events")
        val event: List<Event>,
        @SerializedName("event")
        val searchEvent: List<Event>
)