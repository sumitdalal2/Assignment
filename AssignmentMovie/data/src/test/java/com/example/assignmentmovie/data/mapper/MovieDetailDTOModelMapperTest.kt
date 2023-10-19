package com.example.assignmentmovie.data.mapper

import com.example.assignmentmovie.data.MockData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieDetailDTOModelMapperTest {

    private lateinit var mapper: MovieDetailDTOModelMapper

    @Before
    fun setup() {
        mapper = MovieDetailDTOModelMapper()
    }

    @Test
    fun `MovieDetailsDTOToModelMapper maps input MovieDetailsDTO to MovieDetails domain model`() {
        val mappedMovieDetails = mapper.mapInto(MockData.movieDetailsDTO)
        Assert.assertEquals(mappedMovieDetails.runtime, MockData.movieDetailInfo.runtime)
    }
}
