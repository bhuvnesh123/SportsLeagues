package com.example.sports_data.dto.allcountries

import com.google.gson.annotations.SerializedName

data class CountriesResponseDTO(
    @SerializedName("countries")
    val countries: List<CountryDTO>,
)
