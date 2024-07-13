package com.example.moviepractice.data.remote

import com.example.moviepractice.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page: Int
    ): Response<MovieResponse>

}