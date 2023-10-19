package com.example.assignmentmovie.data.mapper

import com.example.assignmentmovie.data.MockData.movie
import com.example.assignmentmovie.data.MockData.movieDTO
import com.example.assignmentmovie.data.model.MovieListDTO
import com.example.assignmentmovie.domain.model.MovieList
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieListDTOModelMapperTest {
    private lateinit var mapper: MovieListDTOModelMapper

    private val movieDTOModelMapper = mockk<MovieDTOModelMapper>()

    @Before
    fun setup() {
        mapper = MovieListDTOModelMapper(movieDTOModelMapper)
    }


    @Test
    fun `MovieListDtoToModelMapper maps MovieListDTO to MovieList model`() {
        val movieListDTO = MovieListDTO(listOf(movieDTO))
        val movieList = MovieList(listOf(movie))
        coEvery { movieDTOModelMapper.mapInto(any()) } returns (movie)

        val mappedMovieList = mapper.mapInto(movieListDTO)

        Assert.assertEquals(
            mappedMovieList.movies[0].posterPath,
            movieList.movies[0].posterPath
        )
    }


}