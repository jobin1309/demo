package com.example.moviepractice.utils

sealed class NetworkResult<T>(
    var data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(data: T? = null, message: String?) : NetworkResult<T>(data, message)
    class Loading<T>(): NetworkResult<T>()

}
