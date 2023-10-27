package com.example.assignmentmovie.presentation.viewmodel

import com.example.assignmentmovie.common.ViewState
import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.usecase.MoviesListUseCase
import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.presentation.Dispatcher
import com.example.assignmentmovie.presentation.MockData.errorMsg
import com.example.assignmentmovie.presentation.MockData.movie
import com.example.assignmentmovie.presentation.movielist.viewmodel.MovieListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListViewModelTest {

    private val mockMoviesListUseCase: MoviesListUseCase = mockk(relaxed = true)

    private lateinit var movieListViewModel: MovieListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var dispatcher = Dispatcher()

    @Before
    fun setup() {
        movieListViewModel = MovieListViewModel(mockMoviesListUseCase)
    }

    @Test
    fun `getMovieList on Success returns Success ViewState`() = runTest {
        val movies = listOf(movie)
        val response = Response.Success(MovieList(movies))
        val mappedResponse = Response.Success(movies)
        coEvery { mockMoviesListUseCase() } returns flowOf(response)

        movieListViewModel.getMovieList()

        Assert.assertEquals(
            mappedResponse.data[0].id,
            (movieListViewModel.movieListStateFlow.value as ViewState.Success).data[0].id
        )
    }

    @Test
    fun `getMovieList on Error returns Error ViewState`() = runTest {
        val response = Response.Error(errorMsg)
        coEvery { mockMoviesListUseCase() } returns flowOf(response)

        movieListViewModel.getMovieList()

        Assert.assertEquals(
            response.message,
            (movieListViewModel.movieListStateFlow.value as ViewState.Error).message
        )
    }
}