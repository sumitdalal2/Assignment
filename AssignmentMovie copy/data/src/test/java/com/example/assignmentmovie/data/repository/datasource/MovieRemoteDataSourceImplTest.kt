package com.example.assignmentmovie.data.repository.datasource

import com.example.assignmentmovie.common.Constants
import com.example.assignmentmovie.data.BuildConfig
import com.example.assignmentmovie.data.MockData
import com.example.assignmentmovie.data.MockData.IOResponseErrorMessage
import com.example.assignmentmovie.data.MockData.backdropPath
import com.example.assignmentmovie.data.MockData.errorCode
import com.example.assignmentmovie.data.MockData.httpResponseErrorMessage
import com.example.assignmentmovie.data.MockData.id
import com.example.assignmentmovie.data.MockData.movieDetailInfo
import com.example.assignmentmovie.data.MockData.movieDetailsDTO
import com.example.assignmentmovie.data.MockData.posterPath
import com.example.assignmentmovie.data.MockData.responseBody
import com.example.assignmentmovie.data.MockData.responseErrorMessage
import com.example.assignmentmovie.data.api.APIService
import com.example.assignmentmovie.data.dto.MovieDetailsDTO
import com.example.assignmentmovie.data.dto.MovieListDTO
import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.usecase.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class MovieRemoteDataSourceImplTest {

    private val mockTmdbService: APIService = mockk()



    private lateinit var movieRemoteDataSource: MovieRemoteDataSourceImpl

    @Before
    fun setup() {
        movieRemoteDataSource = MovieRemoteDataSourceImpl(
            mockTmdbService
        )
    }

    @Test
    fun `getMovies() on success returns flow of Success ApiResponse`() = runTest {
        val moviesDTO = listOf(MockData.movieDTO)
        val movies = listOf(MockData.movie)
        val movieListDto = MovieListDTO(movieDTO = moviesDTO)
        val mList = MovieList(movies).movies.map {movie->
            movie.copy(posterPath = Constants.IMG_URL + movie.posterPath)
        }
        val expectedMovieList =MovieList(movies =mList)

        coEvery { (mockTmdbService.getMovies(BuildConfig.API_KEY)) } returns (retrofit2.Response.success(
            movieListDto
        ))

        // Act
        val result = movieRemoteDataSource.getMovies().last()

        // Assert
        assertEquals(Response.Success(expectedMovieList), result)
    }

    @Test
    fun `getMovies() on error returns flow of Error ApiResponse`() = runTest {
        // Arrange
        val response = retrofit2.Response.error<MovieListDTO>(
            errorCode, "".toResponseBody(responseBody)
        )
        coEvery { (mockTmdbService.getMovies(BuildConfig.API_KEY)) } returns (response)

        // Act
        val result = movieRemoteDataSource.getMovies().last()

        assertEquals(
            responseErrorMessage, (result as Response.Error).message
        )
    }

    @Test
    fun `getMovies() on HttpException in api call returns flow of Error ApiResponse with exception message`() =
        runTest {
            val response = retrofit2.Response.error<MovieListDTO>(
                errorCode, "".toResponseBody(responseBody)
            )
            coEvery {
                (mockTmdbService.getMovies(BuildConfig.API_KEY))
            } throws HttpException(response)


            // Act
            val result = movieRemoteDataSource.getMovies().last()

            assertEquals(
                Response.Error(httpResponseErrorMessage), result
            )
        }

    @Test
    fun `getMovies() on IOException in api call returns flow of Error ApiResponse with exception message`() =
        runTest {

            coEvery {
                (mockTmdbService.getMovies(BuildConfig.API_KEY))
            } throws IOException(IOResponseErrorMessage)

            // Act
            val result = movieRemoteDataSource.getMovies().last()

            assertEquals(Response.Error(IOResponseErrorMessage), result)
        }

    @Test
    fun `getMovieDetails on success in api call returns flow of Success movie details ApiResponse`() =
        runTest {
            // Arrange
            val movieId = id
            val movieDetailsDto = movieDetailsDTO
            val expectedMovieDetails = movieDetailInfo.copy(posterPath = Constants.IMG_URL+ posterPath, backdropPath = Constants.IMG_URL+ backdropPath)
            coEvery {
                (mockTmdbService.getMovieDetails(
                    movieId, BuildConfig.API_KEY
                ))
            } returns (retrofit2.Response.success(movieDetailsDto))

            // Act
            val result = movieRemoteDataSource.getMovieDetails(movieId).last()

            assertEquals(Response.Success(expectedMovieDetails), result)
        }


    @Test
    fun `getMovieDetails on Error in api call returns flow of Error ApiResponse`() = runTest {
        val movieId = id
        val response = retrofit2.Response.error<MovieDetailsDTO>(
            errorCode, "".toResponseBody(responseBody)
        )
        coEvery {
            (mockTmdbService.getMovieDetails(
                movieId, BuildConfig.API_KEY
            ))
        } returns (response)

        // Act
        val result = movieRemoteDataSource.getMovieDetails(movieId).last()

        assertEquals(
            responseErrorMessage, (result as Response.Error).message
        )

    }

    @Test
    fun `getMovieDetails on HttpException in api call returns flow of Error ApiResponse with exception message`() =
        runTest {
            val movieId = id
            val response = retrofit2.Response.error<MovieListDTO>(
                errorCode, "".toResponseBody(responseBody)
            )
            coEvery {
                (mockTmdbService.getMovieDetails(
                    movieId, BuildConfig.API_KEY
                ))
            } throws HttpException(response)


            // Act
            val result = movieRemoteDataSource.getMovieDetails(movieId).last()

            assertEquals(
                Response.Error(httpResponseErrorMessage), result
            )
        }

    @Test
    fun `getMovieDetails on IOException in api call returns flow of Error ApiResponse with exception message`() =
        runTest {
            val movieId = id
            coEvery {
                (mockTmdbService.getMovieDetails(
                    movieId, BuildConfig.API_KEY
                ))
            } throws IOException(IOResponseErrorMessage)


            // Act
            val result = movieRemoteDataSource.getMovieDetails(movieId).last()

            assertEquals(Response.Error(IOResponseErrorMessage), result)
        }

}