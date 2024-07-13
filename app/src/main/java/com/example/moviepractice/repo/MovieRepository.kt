package com.example.moviepractice.repo

import androidx.lifecycle.LiveData
import com.example.moviepractice.data.local.MovieDao
import com.example.moviepractice.utils.Constants.API_KEY
import com.example.moviepractice.data.remote.MovieApiInterface
import com.example.moviepractice.model.Movie
import com.example.moviepractice.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(private val movieApiInterface: MovieApiInterface, private val movieDao: MovieDao) {

    //REMOTE
    suspend fun getMovieResponse(): Flow<Response<MovieResponse>> =
        flow {
            val response = movieApiInterface.getTopRatedMovies(API_KEY, 1)
            emit(response)
        }

    //LOCAL

    suspend fun insertMovie(movie: Movie) = movieDao.insertMovie(movie)

    fun getMovieOffline(): LiveData<List<Movie>> = movieDao.getAllMovies()

}

