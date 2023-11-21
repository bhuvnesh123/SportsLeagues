package com.example.common

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T) : ApiResult<T>()

    data class GenericError(
        val code: Int? = null,
        val errorMessage: String? = null
    ) : ApiResult<Nothing>()

    object NetworkError : ApiResult<Nothing>()
}

fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> {
    return when (this) {
        is ApiResult.Success -> ApiResult.Success(transform(value))
        is ApiResult.GenericError -> ApiResult.GenericError(code, errorMessage)
        is ApiResult.NetworkError -> ApiResult.NetworkError
    }
}


