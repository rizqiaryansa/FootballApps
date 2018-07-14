package com.aryansa.rizqi.footballapps.api

import java.net.URL

class ApiRequest {
    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}