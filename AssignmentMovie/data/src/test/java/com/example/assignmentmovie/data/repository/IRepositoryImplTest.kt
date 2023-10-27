package com.example.assignmentmovie.data.repository

import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.data.MockData.id
import com.example.assignmentmovie.data.MockData.movieDetailInfo
import com.example.assignmentmovie.data.repository.datasource.MovieRemoteDataSource
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.repository.IRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IRepositoryImplTest {

    private val mockMovieRemoteDataSource: MovieRemoteDataSource = mockk()

    private lateinit var IRepository: IRepository

    @Before
    fun setup() {
        IRepository = IRepositoryImpl(mockMovieRemoteDataSource)
    }

    @Test
    fun `getMovies on getting result from MovieRemoteDataSource returns movie list as flow of Success ApiResponse`() =
        runTest {
            // Arrange
            val expectedResponse = Response.Success(MovieList(emptyList()))
            coEvery { (mockMovieRemoteDataSource.getMovies()) } returns (flow {
                emit(
                    expectedResponse
                )
            })

            // Act
            val resultFlow: Flow<Response<MovieList>> = IRepository.getMovies()

            // Assert
            val resultList: List<Response<MovieList>> = resultFlow.toList()
            assertEquals(listOf(expectedResponse), resultList)
        }

    @Test
    fun `getMovieDetails on success returns movie details as ApiResponse`() = runTest {
        // Arrange
        val movieId = id
        val expectedResponse = Response.Success(
            movieDetailInfo
        )
        coEvery { (mockMovieRemoteDataSource.getMovieDetails(movieId)) } returns (flow {
            emit(
                expectedResponse
            )
        })

        // Act
        val resultFlow: Flow<Response<MovieDetailInfo>> = IRepository.getMovieDetails(movieId)

        // Assert
        val result: Response<MovieDetailInfo> = resultFlow.last()
        assertEquals(expectedResponse, result)
    }
}