package com.example.sports_data.mappers.error

import com.example.sports_data.dto.error.ErrorResponseDTO
import com.example.sports_data.utils.NetworkConstants
import com.example.sports_domain.domainmodels.error.ErrorModel
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    fun map(input: ErrorResponseDTO): ErrorModel =
        with(input) {
            ErrorModel(
                cause = cause.orEmpty(),
                message = message ?: NetworkConstants.UNKNOWN_ERROR,
            )
        }
}
