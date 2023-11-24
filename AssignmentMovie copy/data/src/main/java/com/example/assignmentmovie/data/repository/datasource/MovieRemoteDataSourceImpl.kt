package com.example.assignmentmovie.data.repository.datasource

import com.example.assignmentmovie.common.Constants
import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.data.BuildConfig
import com.example.assignmentmovie.data.api.APIService
import com.example.assignmentmovie.data.dto.MovieDetailsDTO
import com.example.assignmentmovie.data.dto.MovieListDTO
import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService,
) : MovieRemoteDataSource {
    private suspend fun <T> executeApiCall(apiCall: suspend () -> retrofit2.Response<T>): Response<T> =
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = requireNotNull(response.body()) { "Response body is null." }
                Response.Success(body)
            } else {
                Response.Error(response.message())
            }
        } catch (e: HttpException) {
            Response.Error(e.localizedMessage ?: "")
        } catch (e: IOException) {
            Response.Error(e.localizedMessage ?: "")
        }

    override suspend fun getMovies(): Flow<Response<MovieList>> = flow {
        val response = executeApiCall { apiService.getMovies(BuildConfig.API_KEY) }
        if (response is Response.Success) {
            emit(response.data)
        }else if (response is Response.Error) {
            emit(response.message)
        }
    }.transform { data ->
        if (data is MovieListDTO) {
            emit(Response.Success(MovieList(movies = data.movieDTO.map { from ->
                Movie(
                    id = from.id,
                    posterPath = Constants.IMG_URL + from.posterPath,
                    releaseDate = from.releaseDate,
                    title = from.title,
                )
            })))
        }else{
            emit(Response.Error(data.toString()))
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Response<MovieDetailInfo>> = flow {
        val response =
            executeApiCall { apiService.getMovieDetails(movieId, BuildConfig.API_KEY) }
        if (response is Response.Success) {
            emit(response.data)
        } else if (response is Response.Error) {
            emit(response.message)
        }

    }.transform { data ->
        // Map the successful response to MovieDetailInfo
        if (data is MovieDetailsDTO) {
            emit(
                Response.Success(
                    MovieDetailInfo(
                        id = data.id,
                        overview = data.overview,
                        releaseDate = data.releaseDate,
                        runtime = "${data.runtime} ${Constants.MINUTES}",
                        tagline = data.tagline,
                        posterPath = Constants.IMG_URL + data.posterPath,
                        title = data.title,
                        backdropPath = Constants.IMG_URL + data.backdropPath
                    )
                )
            )
        } else {
            // Handle unexpected data and emit an error response
            emit(Response.Error(data.toString()))
        }
    }

}
