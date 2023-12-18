package com.example.sports_data.repository

import com.example.common.UIText
import com.example.sports_data.common.MainCoroutineRule
import com.example.sports_data.repository.fakes.FakeSportsService
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MainCoroutineRule::class)
internal class SportsRepositoryImplTest {
    private val sportsService = FakeSportsService()
    private lateinit var sportsRepositoryImpl: SportsRepositoryImpl

    @BeforeEach
    fun setUp() {
        sportsRepositoryImpl = SportsRepositoryImpl(sportsService = sportsService)
    }

    @Test
    fun `GIVEN service's success response WHEN getAllCountries called THEN emit expected result`() =
        runTest {
            sportsService.setShouldEmitError<CountriesListModel>(isError = false)

            val firstItem = sportsRepositoryImpl.getAllCountries().first()

            assertEquals(
                ApiResult.Success(value = sportsService.getCountryList()),
                firstItem,
            )
        }

    @ParameterizedTest(name = "GIVEN service response {0} WHEN repository getAllCountries called THEN emit {0}")
    @MethodSource("countryListErrorResult")
    fun `GIVEN service's error response WHEN repository's getAllCountries called THEN emit expected result`(
        apiResult: ApiResult<CountriesListModel>,
    ) =
        runTest {
            sportsService.setShouldEmitError(
                isError = true,
                apiResult = apiResult,
            )

            val firstItem = sportsRepositoryImpl.getAllCountries().first()

            assertEquals(
                apiResult,
                firstItem,
            )
        }

    @Test
    fun `GIVEN a country name and service's success response WHEN repository's searchLeaguesByCountry called THEN emit expected result`() =
        runTest {
            sportsService.setShouldEmitError<LeagueListModel>(isError = false)

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = COUNTRY_NAME).first()

            assertEquals(
                ApiResult.Success(value = sportsService.getLeagues()),
                firstItem,
            )
        }

    @ParameterizedTest(name = "GIVEN a country name and service response {0} WHEN repository searchLeaguesByCountry called THEN emit {0}")
    @MethodSource("searchLeaguesErrorResult")
    fun `GIVEN a country name and service's error response WHEN repository's searchLeaguesByCountry called THEN emit expected result`(
        apiResult: ApiResult<LeagueListModel>,
    ) =
        runTest {
            sportsService.setShouldEmitError(
                isError = true,
                apiResult = apiResult,
            )

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = COUNTRY_NAME).first()

            assertEquals(
                apiResult,
                firstItem,
            )
        }

    private companion object {
        const val CHECK_INTERNET_CONNECTION = "Please check your internet connection"
        const val BAD_REQUEST = "Bad Request"
        const val COUNTRY_NAME = "India"

        @JvmStatic
        fun countryListErrorResult() = listOf(
            ApiResult.GenericError(
                code = 400,
                errorMessage = UIText.DynamicString(
                    input = BAD_REQUEST,
                ),
            ),
            ApiResult.NetworkError(
                UIText.DynamicString(input = CHECK_INTERNET_CONNECTION),
            ),

        )

        @JvmStatic
        fun searchLeaguesErrorResult() = listOf(
            ApiResult.GenericError(
                code = 400,
                errorMessage = UIText.DynamicString(
                    input = BAD_REQUEST,
                ),
            ),
            ApiResult.NetworkError(
                errorMessage = UIText.DynamicString(
                    input = CHECK_INTERNET_CONNECTION,
                ),
            ),
        )
    }
}
