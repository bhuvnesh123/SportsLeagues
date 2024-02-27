package com.example.sports_domain.domainmodels.wrapper

import com.example.sports_domain.domainmodels.error.ErrorModel

sealed interface ApiResult<out T> {

    data class Success<out T>(val value: T) : ApiResult<T>

    data class ErrorResponse(
        val code: Int? = null,
        val errorModel: ErrorModel,
    ) : ApiResult<Nothing>

    data class GenericError(
        val code: Int? = null,
        val errorMessage: String,
    ) : ApiResult<Nothing>

    data class NetworkError(val errorMessage: String) : ApiResult<Nothing>
}
