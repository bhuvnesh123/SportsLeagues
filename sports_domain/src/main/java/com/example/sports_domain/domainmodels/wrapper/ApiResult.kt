package com.example.sports_domain.domainmodels.wrapper

import com.example.common.UIText

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T? = null) : ApiResult<T>()

    data class GenericError(
        val code: Int? = null,
        val errorMessage: UIText? = null
    ) : ApiResult<Nothing>()

    data class NetworkError(val errorMessage: UIText) : ApiResult<Nothing>()
}


