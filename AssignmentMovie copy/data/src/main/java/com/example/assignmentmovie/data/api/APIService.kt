package com.example.assignmentmovie.data.api

import com.example.assignmentmovie.data.dto.MovieDetailsDTO
import com.example.assignmentmovie.data.dto.MovieListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apikey: String): Response<MovieListDTO>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieID: Int, @Query("api_key") apikey: String): Response<MovieDetailsDTO>

}