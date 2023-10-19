package com.example.assignmentmovie.data.mapper

import com.example.assignmentmovie.common.Constants
import com.example.assignmentmovie.data.model.MovieDTO
import com.example.assignmentmovie.data.model.MovieListDTO
import com.example.assignmentmovie.domain.model.Movie
import com.example.assignmentmovie.domain.model.MovieList
import javax.inject.Inject

class MovieListDTOModelMapper @Inject constructor(private val movieDTOModelMapper: MovieDTOModelMapper){
     fun mapInto(from: MovieListDTO): MovieList {
        return MovieList(movies = from.movieDTO.map { data -> movieDTOModelMapper.mapInto(data) })
    }
}


class MovieDTOModelMapper @Inject constructor() {
     fun mapInto(from: MovieDTO): Movie {
        return Movie(
            id = from.id,
            posterPath = Constants.IMG_URL + from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
        )
    }
}