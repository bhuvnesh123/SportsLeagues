package com.example.sports_data.dto.countryleagues

import com.google.gson.annotations.SerializedName

data class LeagueResponseDTO(
    @SerializedName("countries")
    val countries: List<LeagueDTO>?,
)
