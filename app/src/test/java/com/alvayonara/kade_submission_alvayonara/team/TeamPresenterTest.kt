package com.alvayonara.kade_submission_alvayonara.team

import com.alvayonara.kade_submission_alvayonara.api.ApiRepository
import com.alvayonara.kade_submission_alvayonara.coroutines.TestContextProvider
import com.alvayonara.kade_submission_alvayonara.model.Team
import com.alvayonara.kade_submission_alvayonara.presenter.MatchDetailPresenter
import com.alvayonara.kade_submission_alvayonara.response.TeamResponse
import com.alvayonara.kade_submission_alvayonara.view.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetailData() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamIdHome = "133604"
        val teamIdAway = "133664"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.getTeamDetailData(teamIdHome, teamIdAway)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetail(teams, teams)
            Mockito.verify(view).hideLoading()
        }
    }
}