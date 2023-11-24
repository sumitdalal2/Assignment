package com.example.assignmentmovie.domain.usecase

import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.repository.IRepository
import com.example.assignmentmovie.domain.usecase.MockData.errorMsg
import com.example.assignmentmovie.domain.usecase.MockData.movie
import com.example.assignmentmovie.domain.usecase.MockData.title
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MoviesListUseCaseTest {

    private val mockIRepository = mockk<IRepository>()

    private lateinit var moviesListUseCase: MoviesListUseCase

    @Before
    fun setup() {
        moviesListUseCase = MoviesListUseCase(mockIRepository)
    }

    @Test
    fun `getMoviesListUseCase on success returns list of movies as Success ApiResponse`() =
        runTest {
            val movies = listOf(movie)
            val expectedResponse = Response.Success(MovieList(movies))
            coEvery { (mockIRepository.getMovies()) } returns (flow { emit(expectedResponse) })

            val result = moviesListUseCase()

            result.collect { response ->
                assert(response is Response.Success)
                val data = (response as Response.Success).data
                assert(data.movies.size == 1)
                assert(data.movies[0].title == title)
            }
        }

    @Test
    fun `getMoviesListUseCase on error returns error message as Error ApiResponse`() = runTest {
        val errorString = errorMsg
        val expectedResponse = Response.Error(errorString)
        coEvery { (mockIRepository.getMovies()) } returns (flow { emit(expectedResponse) })

        val result = moviesListUseCase()

        result.collect { response ->
            assert(response is Response.Error)
            val errorMsg = (response as Response.Error).message
            Assert.assertEquals(errorMsg, errorString)
        }
    }
}