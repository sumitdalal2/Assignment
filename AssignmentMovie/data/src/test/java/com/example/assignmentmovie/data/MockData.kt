package com.example.assignmentmovie.data

import com.example.assignmentmovie.data.model.MovieDTO
import com.example.assignmentmovie.data.model.MovieDetailsDTO
import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import okhttp3.MediaType.Companion.toMediaTypeOrNull

object MockData {
    const val id = 12344
    private const val backdropPath = "path.jpeg"
    private const val posterPath = "poster.jpeg"
    private const val overview = "Overview of Movie 1"
    private const val title = "Movie 1"
    private const val releaseDate = "2022-10-09"
    private const val tagline = "Movie 1 Tag"
    private const val runtime = 120
    private const val runtimeString = "120 min"
    const val errorCode = 404
    val responseBody = "application/json".toMediaTypeOrNull()
    const val responseErrorMessage = "Response.error()"
    const val httpResponseErrorMessage = "HTTP 404 Response.error()"
    const val IOResponseErrorMessage = "IO Error"

    val movieDetailsDTO = MovieDetailsDTO(
        backdropPath,
        id,
        overview,
        posterPath,
        releaseDate,
        runtime,
        tagline,
        title,

        )
    val movieDetailInfo = MovieDetailInfo(
        id,
        backdropPath,
        overview,
        posterPath,
        releaseDate,
        runtimeString,
        tagline,
        title,

        )
    val movieDTO = MovieDTO(
        id,
        posterPath,
        releaseDate,
        title
    )
    val movie = Movie(
        id,
        posterPath,
        releaseDate,
        title
    )
}