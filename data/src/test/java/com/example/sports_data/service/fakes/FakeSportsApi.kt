package com.example.sports_data.service.fakes

import com.example.sports_data.api.SportsApi
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import kotlinx.coroutines.delay

class FakeSportsApi : SportsApi {

    private var apiException: Exception? = null
    private var isTimeout: Boolean = false

    override suspend fun getAllCountries(): CountriesResponseDTO {
        return when {
            apiException != null -> throw apiException!!
            isTimeout -> {
                delay(DELAY)
                CountriesResponseDTO(countries = getCountriesList())
            }
            else -> CountriesResponseDTO(countries = getCountriesList())
        }
    }

    override suspend fun searchLeaguesByCountry(countryName: String): LeagueResponseDTO {
        return when {
            apiException != null -> throw apiException!!
            isTimeout -> {
                delay(DELAY)
                LeagueResponseDTO(countries = getLeaguesList())
            }
            else -> {
                LeagueResponseDTO(countries = getLeaguesList())
            }
        }
    }

    fun setShouldThrowException(exception: Exception = Exception()) {
        apiException = exception
    }
    fun setTimeOut() {
        isTimeout = true
    }
    fun getCountriesList() = listOf(
        CountryDTO(name = "Country 1"),
        CountryDTO(name = "Country 2"),
        CountryDTO(name = "Country 3"),
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

    private companion object {
        const val DELAY = 7000L
    }
}
