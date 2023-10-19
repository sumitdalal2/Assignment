package com.example.assignmentmovie.presentation

import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.domain.model.MovieDetailInfo

object MockData {
    private const val id = 12344
    private const val backdropPath = "path.jpeg"
    private const val posterPath = "poster.jpeg"
    private const val overview = "Overview of Movie 1"
    private const val title = "Movie 1"
    private const val releaseDate = "2022-10-09"
    private const val tagline = "Movie 1 Tag"
    private const val runtime = "120"
    const val errorMsg = "Internal Error"

    val movieDetailInfo = MovieDetailInfo(
        id,
        overview,
        posterPath,
        tagline,
        releaseDate,
        runtime,
        title,
        backdropPath
    )
    val movie = Movie(
        id,
        posterPath,
        releaseDate,
        title
    )
}