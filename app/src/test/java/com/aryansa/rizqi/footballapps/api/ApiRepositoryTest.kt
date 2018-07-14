package com.aryansa.rizqi.footballapps.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun testSearchEventByName() {
        val apiRepository = mock(ApiRequest::class.java)
        val url = TheSportDBApi.getSearchEventByName("Chelsea")
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testSearchTeam() {
        val apiRepository = mock(ApiRequest::class.java)
        val url = TheSportDBApi.getSearchTeamByName("Liverpool")
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}