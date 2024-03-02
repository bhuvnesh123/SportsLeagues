package com.example.sports_data.service

import com.example.sports_data.common.MainCoroutineRule
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.allcountries.CountryMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesMapper
import com.example.sports_data.service.fakes.FakeSportsApi
import com.example.sports_data.utils.NetworkConstants
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
@ExtendWith(MainCoroutineRule::class)
internal class SportsServiceImplTest {

    private val fakeSportsApi = FakeSportsApi()
    private val countriesListMapper = CountriesListMapper(countryMapper = CountryMapper())
    private val leaguesListMapper = LeaguesListMapper(leaguesMapper = LeaguesMapper())

    private lateinit var sportsServiceImpl: SportsServiceImpl

    @BeforeEach
    fun setup() {
        sportsServiceImpl = SportsServiceImpl(
            sportsApi = fakeSportsApi,
            countriesListMapper = countriesListMapper,
            leaguesListMapper = leaguesListMapper,
        )
    }

    @Test
    fun `GIVEN all_countries api success response WHEN service getAllCountries called THEN emit expected mapped ApiResult`() =
        runTest {
            val result = sportsServiceImpl.getAllCountries()

            val expectedResponse =
                CountriesResponseDTO(countries = fakeSportsApi.getCountriesList())
            val expectedApiResult =
                ApiResult.Success(value = countriesListMapper.map(expectedResponse))
            assertEquals(expectedApiResult, result)
        }

    @ParameterizedTest(name = "GIVEN all_countries api throws {0} WHEN service getAllCountries called called THEN emit {1}")
    @MethodSource("apiThrowsException")
    fun `GIVEN all_countries api throws exception WHEN service getAllCountries called THEN emit expectedApiResult`(
        exception: java.lang.Exception,
        apiResult: ApiResult<CountriesListModel>,
    ) = runTest {
        fakeSportsApi.setShouldThrowException(
            exception = exception,
        )

        val result = sportsServiceImpl.getAllCountries()

        assertEquals(apiResult, result)
    }

    @Test
    fun `GIVEN all_countries api timeout WHEN service getAllCountries called THEN emit expected ApiResult with NETWORK_TIMEOUT_TEXT`() =
        runTest {
            fakeSportsApi.setTimeOut()

            val result = sportsServiceImpl.getAllCountries()

            val expectedApiResult =
                ApiResult.GenericError(
                    code = TIMEOUT_ERROR_CODE,
                    errorMessage = NetworkConstants.NETWORK_TIMEOUT_TEXT,
                )
            assertEquals(expectedApiResult, result)
        }

    @Test
    fun `GIVEN search_all_leagues api success response WHEN service searchLeaguesByCountry called THEN emit expected mapped ApiResult`() =
        runTest {
            val result = sportsServiceImpl.searchLeaguesByCountry(
                countryName = COUNTRY_NAME,
            )

            val expectedResponse = LeagueResponseDTO(countries = fakeSportsApi.getLeaguesList())
            val expectedApiResult =
                ApiResult.Success(value = leaguesListMapper.map(expectedResponse))
            assertEquals(expectedApiResult, result)
        }

    @ParameterizedTest(name = "GIVEN search_all_leagues api throws {0} WHEN service searchLeaguesByCountry called called THEN emit {1}")
    @MethodSource("apiThrowsException")
    fun `GIVEN search_all_leagues api throws exception WHEN service searchLeaguesByCountry called THEN emit expectedApiResult`(
        exception: java.lang.Exception,
        apiResult: ApiResult<LeagueListModel>,
    ) = runTest {
        fakeSportsApi.setShouldThrowException(exception = exception)

        val result = sportsServiceImpl.searchLeaguesByCountry(
            countryName = COUNTRY_NAME,
        )

        assertEquals(apiResult, result)
    }

    @Test
    fun `GIVEN search_all_leagues api timeout WHEN service searchLeaguesByCountry called THEN emit expected ApiResult with NETWORK_TIMEOUT_TEXT`() =
        runTest {
            fakeSportsApi.setTimeOut()

            val result = sportsServiceImpl.searchLeaguesByCountry(
                countryName = COUNTRY_NAME,
            )

            val expectedApiResult =
                ApiResult.GenericError(
                    code = TIMEOUT_ERROR_CODE,
                    errorMessage = NetworkConstants.NETWORK_TIMEOUT_TEXT,
                )
            assertEquals(expectedApiResult, result)
        }

    private companion object {
        const val ERROR_MESSAGE = "Page Not Found"
        const val COUNTRY_NAME = "India"
        const val TIMEOUT_ERROR_CODE = 408
        const val NOT_FOUND_CODE = 404

        @JvmStatic
        fun apiThrowsException() = listOf(
            arrayOf(
                Exception(),
                ApiResult.GenericError(
                    errorMessage = NetworkConstants.UNKNOWN_NETWORK_ERROR,
                ),
            ),
            arrayOf(
                IOException(),
                ApiResult.NetworkError(errorMessage = NetworkConstants.NETWORK_ERROR),
            ),
            arrayOf(
                HttpException(
                    Response.error<Any>(
                        NOT_FOUND_CODE,
                        ERROR_MESSAGE.toResponseBody(),
                    ),
                ),
                ApiResult.GenericError(
                    code = NOT_FOUND_CODE,
                    errorMessage = ERROR_MESSAGE,
                ),
            ),
            arrayOf(
                HttpException(
                    Response.error<Any>(
                        NOT_FOUND_CODE,
                        "".toResponseBody(),
                    ),
                ),
                ApiResult.GenericError(
                    code = NOT_FOUND_CODE,
                    errorMessage = NetworkConstants.UNKNOWN_ERROR,
                ),
            ),
        )
    }
}
