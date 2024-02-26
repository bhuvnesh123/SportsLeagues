package com.example.sports_data.utils

import com.example.common.UIText
import com.example.sports_data.dto.error.ErrorResponseDTO
import com.example.sports_data.utils.NetworkConstants.NETWORK_TIMEOUT
import com.example.sports_domain.domainmodels.error.ErrorModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
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
    dispatcher: CoroutineDispatcher,
): ApiResult<R> = withContext(context = dispatcher) {
    try {
        // throws TimeoutCancellationException
        withTimeout(timeMillis = NETWORK_TIMEOUT) {
            val errorMessage =
                UIText.StringResource(id = com.example.common.R.string.error_retrieving_data)

            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let { ApiResult.Success(value = mapper(it)) }
                    ?: ApiResult.GenericError(errorMessage = errorMessage)
            } else {
                response.errorBody()?.let {
                    val errorBody = getErrorResponse(it.string())
                    ApiResult.ErrorResponse(code = response.code(), errorModel = errorMapper(errorBody))
                } ?: ApiResult.GenericError(errorMessage = errorMessage)
            }
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is TimeoutCancellationException -> {
                val code = TIMEOUT_ERROR_CODE // timeout error code
                ApiResult.GenericError(
                    code = code,
                    errorMessage = UIText.StringResource(id = com.example.common.R.string.network_timeout),
                )
            }
            is IOException -> {
                ApiResult.NetworkError(
                    errorMessage = UIText.StringResource(
                        id = com.example.common.R.string.network_error,
                    ),
                )
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable = throwable)
                val errorMessage = if (errorResponse != null) {
                    UIText.DynamicString(input = errorResponse)
                } else {
                    UIText.StringResource(id = com.example.common.R.string.unknown_error)
                }

                ApiResult.GenericError(
                    code = code,
                    errorMessage = errorMessage,
                )
            }
            else -> {
                ApiResult.GenericError(
                    errorMessage = UIText.StringResource(
                        id = com.example.common.R.string.unknown_network_error,
                    ),
                )
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? = try {
    throwable.response()?.errorBody()?.string()
} catch (exception: Exception) {
    null
}

private fun getErrorResponse(response: String): ErrorResponseDTO {
    return Gson().fromJson(response, ErrorResponseDTO::class.java)
}
