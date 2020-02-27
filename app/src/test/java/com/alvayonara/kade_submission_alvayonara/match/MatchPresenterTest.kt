package com.alvayonara.kade_submission_alvayonara.match

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.coroutines.TestContextProvider
import com.alvayonara.kade_submission_alvayonara.model.Match
import com.alvayonara.kade_submission_alvayonara.presenter.MatchPresenter
import com.alvayonara.kade_submission_alvayonara.response.MatchResponse
import com.alvayonara.kade_submission_alvayonara.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.*

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(response)

            presenter.getMatchList(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matches, matches)
            Mockito.verify(view).hideLoading()
        }
    }
}