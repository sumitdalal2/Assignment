package com.example.assignmentmovie.data.repository.datasource

import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getMovies(): Flow<Response<MovieList>>
    suspend fun getMovieDetails(movieId: Int): Flow<Response<MovieDetailInfo>>
}