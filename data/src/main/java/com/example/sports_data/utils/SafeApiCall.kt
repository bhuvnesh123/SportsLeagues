package com.example.sports_data.utils

import com.example.sports_data.dto.error.ErrorResponseDTO
import com.example.sports_data.utils.NetworkConstants.NETWORK_TIMEOUT
import com.example.sports_domain.domainmodels.error.ErrorModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

private const val TIMEOUT_ERROR_CODE = 408

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R,
    errorMapper: (ErrorResponseDTO) -> ErrorModel,
): ApiResult<R> = try {
    // throws TimeoutCancellationException
    withTimeout(timeMillis = NETWORK_TIMEOUT) {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { ApiResult.Success(value = mapper(it)) }
                ?: ApiResult.GenericError(errorMessage = NetworkConstants.ERROR_RETRIEVING_DATA)
        } else {
            response.errorBody()?.let {
                val errorBody = getErrorResponse(it.string())
                ApiResult.ErrorResponse(code = response.code(), errorModel = errorMapper(errorBody))
            } ?: ApiResult.GenericError(errorMessage = NetworkConstants.ERROR_RETRIEVING_DATA)
        }
    }
} catch (throwable: Throwable) {
    when (throwable) {
        is TimeoutCancellationException -> {
            ApiResult.GenericError(
                code = TIMEOUT_ERROR_CODE,
                errorMessage = NetworkConstants.NETWORK_TIMEOUT_TEXT,
            )
        }
        is IOException -> {
            ApiResult.NetworkError(
                errorMessage = NetworkConstants.NETWORK_ERROR,
            )
        }
        is HttpException -> {
            val code = throwable.code()
            val errorResponse = convertErrorBody(throwable = throwable)
            val errorMessage = if (errorResponse.isNullOrEmpty()) NetworkConstants.UNKNOWN_ERROR else errorResponse

            ApiResult.GenericError(
                code = code,
                errorMessage = errorMessage,
            )
        }
        else -> {
            ApiResult.GenericError(
                errorMessage = NetworkConstants.UNKNOWN_NETWORK_ERROR,
            )
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? = try {
    throwable.response()?.errorBody()?.string()
} catch (exception: Exception) {
    null
}

private fun getErrorResponse(response: String): ErrorResponseDTO =
    Gson().fromJson(response, ErrorResponseDTO::class.java)
