package com.example.sports_data.service

import com.example.common.UIText
import com.example.sports_data.common.MainCoroutineRule
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.allcountries.CountryMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesMapper
import com.example.sports_data.service.fakes.FakeSportsApi
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
            fakeSportsApi.setShouldThrowException(shouldThrow = false)

            val result = sportsServiceImpl.getAllCountries().first()

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
            shouldThrow = true,
            exception = exception,
        )

        val result = sportsServiceImpl.getAllCountries().first()

        assertEquals(apiResult, result)
    }

    @Test
    fun `GIVEN search_all_leagues api success response WHEN service searchLeaguesByCountry called THEN emit expected mapped ApiResult`() =
        runTest {
            fakeSportsApi.setShouldThrowException(shouldThrow = false)

            val result =
                sportsServiceImpl.searchLeaguesByCountry(countryName = COUNTRY_NAME).first()

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
        fakeSportsApi.setShouldThrowException(shouldThrow = true, exception = exception)

        val result = sportsServiceImpl.searchLeaguesByCountry(countryName = COUNTRY_NAME).first()

        assertEquals(apiResult, result)
    }

    private companion object {
        const val ERROR_MESSAGE = "Page Not Found"
        const val COUNTRY_NAME = "India"

        @JvmStatic
        fun apiThrowsException() = listOf(
            arrayOf(
                Exception(),
                ApiResult.GenericError(
                    errorMessage = UIText.StringResource(id = com.example.common.R.string.unknown_network_error),
                ),
            ),
            arrayOf(
                IOException(),
                ApiResult.NetworkError(errorMessage = UIText.StringResource(id = com.example.common.R.string.network_error)),
            ),
            arrayOf(
                HttpException(
                    Response.error<Any>(
                        404,
                        okhttp3.ResponseBody.create(null, ERROR_MESSAGE),
                    ),
                ),
                ApiResult.GenericError(
                    code = 404,
                    errorMessage = UIText.DynamicString(input = ERROR_MESSAGE),
                ),
            ),
        )
    }
}
