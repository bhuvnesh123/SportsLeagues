package com.example.sports_data.utils

import com.example.sports_data.utils.NetworkConstants.NETWORK_TIMEOUT
import com.example.sports_data.utils.NetworkErrors.ERROR_UNKNOWN
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.example.sports_data.utils.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.example.sports_domain.ApiResult
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
): ApiResult<R?> {

    return try {
        // throws TimeoutCancellationException
        withTimeout(NETWORK_TIMEOUT) {
            val response = apiCall.invoke()
            if (response != null) {
                ApiResult.Success(mapper(response))
            } else {
                ApiResult.Success(null)
            }
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is TimeoutCancellationException -> {
                val code = 408 // timeout error code
                ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
            }
            is IOException -> {
                ApiResult.NetworkError(NETWORK_ERROR)
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ApiResult.GenericError(
                    code,
                    errorResponse
                )
            }
            else -> {
                ApiResult.GenericError(
                    null,
                    NETWORK_ERROR_UNKNOWN
                )
            }
        }
    }

}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        ERROR_UNKNOWN
    }
}