package com.example.assignmentmovie.domain.usecase

import com.example.assignmentmovie.domain.model.MovieList
import com.example.assignmentmovie.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MoviesListUseCase @Inject constructor(private val IRepository: IRepository) {
    suspend operator fun invoke(): Flow<Response<MovieList>> = IRepository.getMovies()
}   



