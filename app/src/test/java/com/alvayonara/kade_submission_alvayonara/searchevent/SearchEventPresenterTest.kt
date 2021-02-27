package com.alvayonara.kade_submission_alvayonara.searchevent

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.coroutines.TestContextProvider
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.SearchEventPresenter
import com.alvayonara.kade_submission_alvayonara.response.SearchEventResponse
import com.alvayonara.kade_submission_alvayonara.view.SearchEventView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchEventPresenterTest {
    @Mock
    private lateinit var view: SearchEventView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchEventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchEventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetSearchEventList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = SearchEventResponse(matches)
        val event = "real madrid"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", SearchEventResponse::class.java)).thenReturn(response)

            presenter.getSearchEventList(event)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}