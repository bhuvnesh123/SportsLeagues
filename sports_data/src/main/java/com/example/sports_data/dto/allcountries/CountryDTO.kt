package com.example.sports_data.dto.allcountries

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name_en")
    val name: String,
)
