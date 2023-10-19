package com.example.assignmentmovie.domain.repository

import com.example.assignmentmovie.common.Response
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getMovies(): Flow<Response<MovieList>>
    suspend fun getMovieDetails(movieID: Int): Flow<Response<MovieDetailInfo>>
}