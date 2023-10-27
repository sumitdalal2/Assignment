package com.example.assignmentmovie.presentation.viewmodel

import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.usecase.MovieDetailsUseCase
import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.presentation.Dispatcher
import com.example.assignmentmovie.presentation.MockData.errorMsg
import com.example.assignmentmovie.presentation.MockData.movieDetailInfo
import com.example.assignmentmovie.presentation.moviedetails.viewmodel.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailInfoViewModelTest {

    private val mockMovieDetailsUseCase: MovieDetailsUseCase = mockk()

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var dispatcher = Dispatcher()

    @Before
    fun setup() {
        movieDetailsViewModel = MovieDetailsViewModel(mockMovieDetailsUseCase)
    }

    @Test
    fun `getMovieDetails on Success returns Success ViewState`() = runTest {
        val response = Response.Success(movieDetailInfo)
        coEvery { mockMovieDetailsUseCase(1) } returns flowOf(response)

        movieDetailsViewModel.getMovieDetails(1)

        assertEquals(
            response.data,
            (movieDetailsViewModel.movieDetailInfoStateFlow.value as ViewState.Success).data
        )
    }

    @Test
    fun `getMovieDetails on Error returns Error ViewState`() = runTest {
        val response =Response.Error(errorMsg)
        coEvery { mockMovieDetailsUseCase(1) } returns flowOf(response)

        movieDetailsViewModel.getMovieDetails(1)

        assertEquals(
            response.message,
            (movieDetailsViewModel.movieDetailInfoStateFlow.value as ViewState.Error).message
        )
    }
}