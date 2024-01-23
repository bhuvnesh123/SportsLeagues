package com.example.sports_data.dto.countryleagues

import com.google.gson.annotations.SerializedName

data class LeagueDTO(
    @SerializedName("strSport")
    val strSport: String?,

    @SerializedName("strLeague")
    val strLeague: String?,

    @SerializedName("strCurrentSeason")
    val strCurrentSeason: String?,

    @SerializedName("intFormedYear")
    val intFormedYear: String?,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?,

    @SerializedName("strTvRights")
    val strTvRights: String?,
)
