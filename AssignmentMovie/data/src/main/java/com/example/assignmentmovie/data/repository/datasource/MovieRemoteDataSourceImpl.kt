package com.example.assignmentmovie.data.repository.datasource

import com.example.assignmentmovie.common.Response
import com.example.assignmentmovie.data.BuildConfig
import com.example.assignmentmovie.data.api.APIService
import com.example.assignmentmovie.data.mapper.MovieDetailDTOModelMapper
import com.example.assignmentmovie.data.mapper.MovieListDTOModelMapper
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService,
    private val movieListDTOModelMapper: MovieListDTOModelMapper,
    private val movieDetailDTOModelMapper: MovieDetailDTOModelMapper
) : MovieRemoteDataSource {

    override suspend fun getMovies(): Flow<Response<MovieList>> = flow {
        emit(Response.Loading)
        try {
            val response = apiService.getMovies(BuildConfig.API_KEY)
            if (response.isSuccessful) {
                response.body()
                    ?.let {
                        emit(
                            Response.Success(
                                movieListDTOModelMapper.mapInto(it)
                            )
                        )
                    }
            } else {
                emit(Response.Error(response.message()))
            }

        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: ""))

        } catch (e: IOException) {
            emit(Response.Error(e.localizedMessage ?: ""))

        }
    }.flowOn(Dispatchers.IO)


    override suspend fun getMovieDetails(movieId: Int): Flow<Response<MovieDetailInfo>> = flow {
        emit(Response.Loading)
        try {

            val response = apiService.getMovieDetails(movieId, BuildConfig.API_KEY)
            if (response.isSuccessful) {

                response.body()
                    ?.let { emit(Response.Success(movieDetailDTOModelMapper.mapInto(it))) }
            } else {
                emit(Response.Error(response.message()))
            }

        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: ""))

        } catch (e: IOException) {
            emit(Response.Error(e.localizedMessage ?: ""))

        }
    }.flowOn(Dispatchers.IO)
}

