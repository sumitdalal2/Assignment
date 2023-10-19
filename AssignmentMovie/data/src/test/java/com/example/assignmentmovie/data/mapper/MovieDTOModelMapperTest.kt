package com.example.assignmentmovie.data.mapper

import com.example.assignmentmovie.data.MockData.movie
import com.example.assignmentmovie.data.MockData.movieDTO
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieDTOModelMapperTest {
    private lateinit var mapper: MovieDTOModelMapper

    @Before
    fun setup() {
        mapper = MovieDTOModelMapper()
    }


    @Test
    fun `MovieDtoToModelMapper maps MovieListDTO to MovieList model`() {
        val mappedMovie = mapper.mapInto(movieDTO)

        Assert.assertEquals(mappedMovie.title, movie.title)
    }



}