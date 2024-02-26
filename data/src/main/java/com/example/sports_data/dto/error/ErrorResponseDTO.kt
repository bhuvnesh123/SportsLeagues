package com.example.sports_data.dto.error

import com.google.gson.annotations.SerializedName

data class ErrorResponseDTO(
    @SerializedName("cause")
    val cause: String?,
    @SerializedName("message")
    val message: String?,
)
