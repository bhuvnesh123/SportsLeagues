package com.example.sports_data.utils

import com.example.sports_data.utils.NetworkConstants.NETWORK_TIMEOUT
import com.example.sports_data.utils.NetworkErrors.ERROR_UNKNOWN
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> T?,
    mapper: (T) -> R
): ApiResult<R?> = try {
    // throws TimeoutCancellationException
    withTimeout(timeMillis = NETWORK_TIMEOUT) {
        val response = apiCall.invoke()
        if (response != null) {
            ApiResult.Success(value = mapper(response))
        } else {
            ApiResult.Success()
        }
    }
} catch (throwable: Throwable) {
    throwable.printStackTrace()
    when (throwable) {
        is TimeoutCancellationException -> {
            val code = 408 // timeout error code
            ApiResult.GenericError(
                code = code,
                errorMessage = NETWORK_ERROR_TIMEOUT
            )
        }
        is IOException -> {
            ApiResult.NetworkError(errorMessage = NETWORK_ERROR)
        }
        is HttpException -> {
            val code = throwable.code()
            val errorResponse = convertErrorBody(throwable = throwable)
            ApiResult.GenericError(
                code = code,
                errorMessage = errorResponse
            )
        }
        else -> {
            ApiResult.GenericError(
                errorMessage = NETWORK_ERROR_UNKNOWN
            )
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? = try {
    throwable.response()?.errorBody()?.string()
} catch (exception: Exception) {
    ERROR_UNKNOWN
}
