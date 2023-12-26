package com.example.sports_data.service.fakes

import com.example.sports_data.api.SportsApi
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO

class FakeSportsApi : SportsApi {

    private var apiException: Exception? = null

    override suspend fun getAllCountries(): CountriesResponseDTO {
        return apiException?.let { throw it } ?: run {
            CountriesResponseDTO(countries = getCountriesList())
        }
    }

    override suspend fun searchLeaguesByCountry(countryName: String): LeagueResponseDTO {
        return apiException?.let { throw it } ?: run {
            LeagueResponseDTO(countries = getLeaguesList())
        }
    }

    fun setShouldThrowException(shouldThrow: Boolean, exception: Exception = Exception()) {
        apiException = if (shouldThrow) exception else null
    }

    fun getCountriesList() = listOf(
        CountryDTO(name_en = "Country 1"),
        CountryDTO(name_en = "Country 2"),
        CountryDTO(name_en = "Country 3"),
    )

    fun getLeaguesList() =
        listOf(
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
        )
}
