package com.example.sports_domain

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T) : ApiResult<T>()

    data class GenericError(
        val code: Int? = null,
        val errorMessage: String? = null
    ) : ApiResult<Nothing>()

    data class NetworkError(val errorMessage: String) : ApiResult<Nothing>()
}


