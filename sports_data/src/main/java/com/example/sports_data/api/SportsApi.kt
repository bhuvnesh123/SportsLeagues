package com.example.sports_data.api

import com.example.sports_data.dto.allCountries.CountriesResponse
import com.example.sports_data.dto.countryLeagues.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("all_countries.php")
    suspend fun getAllCountries(
    ): CountriesResponse

    @GET("search_all_leagues.php")
    suspend fun searchLeaguesByCountry(@Query("c") countryName: String): LeagueResponse
}