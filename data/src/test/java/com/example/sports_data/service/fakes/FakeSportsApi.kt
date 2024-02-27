package com.example.sports_data.service.fakes

import com.example.sports_data.api.SportsApi
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeSportsApi : SportsApi {

    private var apiException: Exception? = null
    private var jsonErrorString: String? = null
    private var errorCode: Int = 410
    private var isTimeout: Boolean = false
    private var isNullSuccessBody: Boolean = false

    override suspend fun getAllCountries(): Response<CountriesResponseDTO> {
        return when {
            apiException != null -> throw apiException!!
            jsonErrorString != null -> {
                val errorResponseBody: ResponseBody =
                    jsonErrorString!!.toResponseBody("application/json".toMediaTypeOrNull())
                Response.error(errorCode, errorResponseBody)
            }
            isTimeout -> {
                delay(DELAY)
                Response.success(CountriesResponseDTO(countries = getCountriesList()))
            }
            isNullSuccessBody -> {
                Response.success(null)
            }
            else -> Response.success(CountriesResponseDTO(countries = getCountriesList()))
        }
    }

    override suspend fun searchLeaguesByCountry(countryName: String): Response<LeagueResponseDTO> {
        return when {
            apiException != null -> throw apiException!!
            jsonErrorString != null -> {
                val errorResponseBody: ResponseBody =
                    jsonErrorString!!.toResponseBody("application/json".toMediaTypeOrNull())
                Response.error(errorCode, errorResponseBody)
            }
            isTimeout -> {
                delay(DELAY)
                Response.success(LeagueResponseDTO(countries = getLeaguesList()))
            }
            isNullSuccessBody -> {
                Response.success(null)
            }
            else -> {
                Response.success(LeagueResponseDTO(countries = getLeaguesList()))
            }
        }
    }

    fun setShouldThrowException(exception: Exception = Exception()) {
        apiException = exception
    }
    fun setJsonError(jsonString: String, responseCode: Int) {
        jsonErrorString = jsonString
        errorCode = responseCode
    }
    fun setTimeOut() {
        isTimeout = true
    }
    fun setSuccessBodyNull() {
        isNullSuccessBody = true
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
