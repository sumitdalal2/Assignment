package com.example.assignmentmovie.domain.usecase

import com.example.assignmentmovie.domain.repository.IRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val IRepository: IRepository) {
    suspend operator fun invoke(movieId: Int) = IRepository.getMovieDetails(movieId)
}