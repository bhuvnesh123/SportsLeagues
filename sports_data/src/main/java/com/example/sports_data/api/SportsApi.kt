package com.example.sports_data.api

import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("all_countries.php")
    suspend fun getAllCountries(): CountriesResponseDTO

    @GET("search_all_leagues.php")
    suspend fun searchLeaguesByCountry(@Query("c") countryName: String): LeagueResponseDTO
}
