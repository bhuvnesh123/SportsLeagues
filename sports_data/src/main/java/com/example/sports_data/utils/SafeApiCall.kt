package com.example.sports_data.utils

import com.example.common.UIText
import com.example.sports_data.utils.NetworkConstants.NETWORK_TIMEOUT
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

private const val TIMEOUT_ERROR_CODE = 408

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> T?,
    mapper: (T) -> R
): ApiResult<R?> = try {
    // throws TimeoutCancellationException
    withTimeout(timeMillis = NETWORK_TIMEOUT) {
        val response = apiCall()
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
            val code = TIMEOUT_ERROR_CODE // timeout error code
            ApiResult.GenericError(
                code = code,
                errorMessage = UIText.StringResource(id = com.example.common.R.string.network_timeout)
            )
        }
        is IOException -> {
            ApiResult.NetworkError(errorMessage = UIText.StringResource(id = com.example.common.R.string.network_error))
        }
        is HttpException -> {
            val code = throwable.code()
            val errorResponse = convertErrorBody(throwable = throwable)
            ApiResult.GenericError(
                code = code,
                errorMessage = errorResponse?.let {
                    UIText.DynamicString(input = it)
                }
                    ?: run { UIText.StringResource(id = com.example.common.R.string.unknown_error) }
            )
        }
        else -> {
            ApiResult.GenericError(
                errorMessage = UIText.StringResource(id = com.example.common.R.string.unknown_network_error)
            )
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? = try {
    throwable.response()?.errorBody()?.string()
} catch (exception: Exception) {
    null
}
