package com.example.assignmentmovie.domain.usecase

import com.example.assignmentmovie.domain.repository.IRepository
import com.example.assignmentmovie.domain.usecase.MockData.errorMsg
import com.example.assignmentmovie.domain.usecase.MockData.id
import com.example.assignmentmovie.domain.usecase.MockData.movieDetailInfo
import com.example.assignmentmovie.domain.usecase.MockData.title
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieDetailInfoUseCaseTest {

    private val mockIRepository = mockk<IRepository>()

    private lateinit var movieDetailsUseCase: MovieDetailsUseCase

    @Before
    fun setup() {
        movieDetailsUseCase = MovieDetailsUseCase(mockIRepository)
    }

    @Test
    fun `getMovieDetailsUseCase on Success result from repository returns movie details as Success APiResponse`() =
        runTest {
            val movieId = id
            val title = title
            val expectedResponse = Response.Success(movieDetailInfo)
            coEvery { (mockIRepository.getMovieDetails(movieId)) } returns (flow {
                emit(
                    expectedResponse
                )
            })

            val result = movieDetailsUseCase(movieId)

            result.collect { response ->
                assert(response is Response.Success)
                val data = (response as Response.Success).data
                assert(data.id == movieId)
                assert(data.title == title)
            }
        }

    @Test
    fun `getMovieDetailsUseCase on Error from repository returns error message as Error APiResponse`() =
        runTest {
            val movieId = id
            val errorString = errorMsg
            val expectedResponse = Response.Error(errorString)
            coEvery { (mockIRepository.getMovieDetails(movieId)) } returns (flow {
                emit(
                    expectedResponse
                )
            })

            val result = movieDetailsUseCase(movieId)

            result.collect { response ->
                assert(response is Response.Error)
                val errorMsg = (response as Response.Error).message
                Assert.assertEquals(errorMsg, errorString)
            }
        }
}