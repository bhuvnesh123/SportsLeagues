package com.example.sports_data.mappers.fakes

import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import com.example.sports_data.dto.error.ErrorResponseDTO

object ResponseDTOFake {

    fun getCountryResponseDTO(): CountriesResponseDTO = CountriesResponseDTO(
        countries = listOf(
            CountryDTO(name = "Country 1"),
            CountryDTO(name = "Country 2"),
            CountryDTO(name = "Country 3"),
        ),
    )

    fun getLeagueResponseDTO(): LeagueResponseDTO = LeagueResponseDTO(
        countries = listOf(
            LeagueDTO(
                strSport = "Football",
                strLeague = "League 1",
                strCurrentSeason = "2021-2022",
                intFormedYear = "2000",
                strDescriptionEN = "English description of League 1",
                strTvRights = "TV rights for League 1",
            ),
            LeagueDTO(
                strSport = "Football",
                strLeague = "League 2",
                strCurrentSeason = "2021-2022",
                intFormedYear = "2000",
                strDescriptionEN = "English description of League 2",
                strTvRights = "TV rights for League 2",
            ),
        ),
    )

    fun getErrorResponseDTO(): ErrorResponseDTO = ErrorResponseDTO(
        cause = "No matched country found",
        message = "Please try with different country",
    )
}
