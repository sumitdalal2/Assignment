package com.example.assignmentmovie.domain.model


data class MovieList(
    val movies: List<Movie>,
)

data class Movie(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)
