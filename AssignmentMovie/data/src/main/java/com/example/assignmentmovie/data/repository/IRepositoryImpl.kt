package com.example.assignmentmovie.data.repository

import com.example.assignmentmovie.domain.usecase.Response
import com.example.assignmentmovie.data.repository.datasource.MovieRemoteDataSource
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    IRepository {
    override suspend fun getMovies(): Flow<Response<MovieList>> =
        movieRemoteDataSource.getMovies()

    override suspend fun getMovieDetails(movieID: Int): Flow<Response<MovieDetailInfo>> =
        movieRemoteDataSource.getMovieDetails(movieId = movieID)
}