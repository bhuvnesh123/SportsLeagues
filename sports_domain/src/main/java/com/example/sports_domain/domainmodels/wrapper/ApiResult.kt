package com.example.sports_domain.domainmodels.wrapper

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T? = null) : ApiResult<T>()

    data class GenericError(
        val code: Int? = null,
        val errorMessage: String? = null
    ) : ApiResult<Nothing>()

    data class NetworkError(val errorMessage: String) : ApiResult<Nothing>()
}


