package com.example.moviepractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepractice.model.Movie
import com.example.moviepractice.model.MovieResponse
import com.example.moviepractice.repo.MovieRepository
import com.example.moviepractice.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(application: Application, private val movieRepository: MovieRepository) :
    AndroidViewModel(application) {

    private val _movieResponse: MutableLiveData<List<Movie>?> = MutableLiveData()
    val movieResponse: LiveData<List<Movie>?> = _movieResponse

    val movieDb: LiveData<List<Movie>> = movieRepository.getMovieOffline()


    fun movieResponse() {
        viewModelScope.launch(){
            try {
                movieRepository.getMovieResponse().collect { response ->
                    val fetchedMovies = handleResponse(response).data
                      _movieResponse.value = fetchedMovies?.results
                    Log.d("Movies", fetchedMovies.toString())
                    fetchedMovies?.results?.forEach { movie ->
                        movieRepository.insertMovie(movie)
                    }
                }
            } catch (e: Exception) {
                Log.d("Movies", e.message.toString())
            }
        }

    }

    private fun handleResponse(movieResponse: Response<MovieResponse>): NetworkResult<MovieResponse> {
        when {
            movieResponse.isSuccessful -> {
                Log.d("network", "isSucess")
                val movies = movieResponse.body()
                if (movies != null) {
                    return NetworkResult.Success(movies)
                }
            }

            movieResponse.code() == 402 -> {
                Log.d("network", "402")
                return NetworkResult.Error(message = "API key Limited")
            }
        }
        Log.d("network", "error")
        return NetworkResult.Error(message = "Error")
    }
}


