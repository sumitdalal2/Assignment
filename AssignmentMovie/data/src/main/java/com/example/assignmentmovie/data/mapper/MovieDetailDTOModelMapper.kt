package com.example.assignmentmovie.data.mapper

import com.example.assignmentmovie.common.Constants
import com.example.assignmentmovie.data.dto.MovieDetailsDTO
import com.example.assignmentmovie.domain.model.MovieDetailInfo
import javax.inject.Inject

class MovieDetailDTOModelMapper @Inject constructor()  {
     fun mapInto(from: MovieDetailsDTO): MovieDetailInfo {
        return MovieDetailInfo(
            id = from.id,
            overview = from.overview,
            releaseDate = from.releaseDate,
            runtime = "${from.runtime} ${Constants.MINUTES}",
            tagline = from.tagline,
            posterPath = Constants.IMG_URL + from.posterPath,
            title = from.title,
            backdropPath = Constants.IMG_URL + from.backdropPath
        )
    }
}