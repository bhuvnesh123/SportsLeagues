package com.example.sports_data.repository

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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineRule::class)
internal class SportsRepositoryImplTest {
    private val sportsService: SportsService = mockk()
    private lateinit var sportsRepositoryImpl: SportsRepositoryImpl

    @BeforeEach
    fun setUp() {
        sportsRepositoryImpl = SportsRepositoryImpl(sportsService = sportsService)
    }

    @Test
    fun `GIVEN success response WHEN getAllCountries called THEN emit SUCCESS`() = runTest {

        val countriesListModel = CountriesListModel(countries = mockk())
        coEvery { sportsService.getAllCountries() } returns flow {
            emit(
                ApiResult.Success(
                    countriesListModel
                )
            )
        }

        val firstItem = sportsRepositoryImpl.getAllCountries().first()

        assertEquals(
            ApiResult.Success(
                countriesListModel
            ), firstItem
        )

    }

    @Test
    fun `GIVEN generic error response WHEN getAllCountries called THEN emit GenericError`() =
        runTest {

            coEvery { sportsService.getAllCountries() } returns flow {
                emit(
                    ApiResult.GenericError(
                        400, "Bad Request"
                    )
                )
            }

            val firstItem = sportsRepositoryImpl.getAllCountries().first()

            assertEquals(
                ApiResult.GenericError(
                    400, "Bad Request"
                ), firstItem
            )

        }

    @Test
    fun `GIVEN network error response WHEN getAllCountries called THEN emit NetworkError`() =
        runTest {

            coEvery { sportsService.getAllCountries() } returns flow {
                emit(
                    ApiResult.NetworkError(
                        "Please check your internet connection"
                    )
                )
            }

            val firstItem = sportsRepositoryImpl.getAllCountries().first()

            assertEquals(
                ApiResult.NetworkError(
                    "Please check your internet connection"
                ), firstItem
            )

        }

    @Test
    fun `GIVEN a country name and success response WHEN searchLeaguesByCountry called THEN emit SUCCESS`() =
        runTest {

            val leagueListModel = LeagueListModel(countries = mockk())
            val countryName = "India"
            coEvery { sportsService.searchLeaguesByCountry(countryName = countryName) } returns flow {
                emit(
                    ApiResult.Success(
                        leagueListModel
                    )
                )
            }

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = countryName).first()

            assertEquals(
                ApiResult.Success(
                    leagueListModel
                ), firstItem
            )

        }

    @Test
    fun `GIVEN a country name and generic error response WHEN searchLeaguesByCountry called THEN emit GenericError`() =
        runTest {

            val countryName = "India"
            coEvery { sportsService.searchLeaguesByCountry(countryName = countryName) } returns flow {
                emit(
                    ApiResult.GenericError(
                        400, "Bad Request"
                    )
                )
            }

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = countryName).first()

            assertEquals(
                ApiResult.GenericError(
                    400, "Bad Request"
                ), firstItem
            )

        }

    @Test
    fun `GIVEN a country name and network error response WHEN searchLeaguesByCountry called THEN emit NetworkError`() =
        runTest {

            val countryName = "India"
            coEvery { sportsService.searchLeaguesByCountry(countryName = countryName) } returns flow {
                emit(
                    ApiResult.NetworkError(
                        "Please check your internet connection"
                    )
                )
            }

            val firstItem =
                sportsRepositoryImpl.searchLeaguesByCountry(countryName = countryName).first()

            assertEquals(
                ApiResult.NetworkError(
                    "Please check your internet connection"
                ), firstItem
            )

        }
}
