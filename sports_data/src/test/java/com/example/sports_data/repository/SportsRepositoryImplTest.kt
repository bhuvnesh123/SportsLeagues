package com.example.sports_data.repository

import com.example.common.UIText
import com.example.sports_data.common.MainCoroutineRule
import com.example.sports_data.service.SportsService
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MainCoroutineRule::class)
internal class SportsRepositoryImplTest {
    private val sportsService: SportsService = mockk()
    private lateinit var sportsRepositoryImpl: SportsRepositoryImpl

    @BeforeEach
    fun setUp() {
        sportsRepositoryImpl = SportsRepositoryImpl(sportsService = sportsService)
    }

    @ParameterizedTest(name = "GIVEN response {0} WHEN getAllCountries called THEN emit {0}")
    @MethodSource("countryListTestResponse")
    fun `GIVEN response WHEN getAllCountries called THEN emit expected result`(apiResult: ApiResult<CountriesListModel>) =
        runTest {
            coEvery { sportsService.getAllCountries() } returns flow {
                emit(
                    apiResult,
                )
            }

            val firstItem = sportsRepositoryImpl.getAllCountries().first()

            assertEquals(
                apiResult,
                firstItem,
            )
        }

    @ParameterizedTest(name = "GIVEN a country name and response {0} WHEN searchLeaguesByCountry called THEN emit {0}")
    @MethodSource("searchLeaguesTestResponse")
    fun `GIVEN a country name and response WHEN searchLeaguesByCountry called THEN emit expected result`(
        apiResult: ApiResult<LeagueListModel>,
    ) =
        runTest {
            val countryName = "India"
            coEvery { sportsService.searchLeaguesByCountry(countryName = countryName) } returns flow {
                emit(
                    apiResult,
                )
            }

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = countryName).first()

            assertEquals(
                apiResult,
                firstItem,
            )
        }

    private companion object {
        const val CHECK_INTERNET_CONNECTION = "Please check your internet connection"
        const val BAD_REQUEST = "Bad Request"

        @JvmStatic
        fun countryListTestResponse() = listOf(
            ApiResult.Success(
                CountriesListModel(countries = mockk()),
            ),
            ApiResult.GenericError(
                400,
                UIText.DynamicString(input = BAD_REQUEST),
            ),
            ApiResult.NetworkError(
                UIText.DynamicString(input = CHECK_INTERNET_CONNECTION),
            ),

        )

        @JvmStatic
        fun searchLeaguesTestResponse() = listOf(
            ApiResult.Success(
                LeagueListModel(countries = mockk()),
            ),
            ApiResult.GenericError(
                400,
                UIText.DynamicString(input = BAD_REQUEST),
            ),
            ApiResult.NetworkError(
                UIText.DynamicString(input = CHECK_INTERNET_CONNECTION),
            ),
        )
    }
}
