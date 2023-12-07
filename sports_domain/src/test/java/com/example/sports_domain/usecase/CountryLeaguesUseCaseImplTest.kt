package com.example.sports_domain.usecase

import com.example.sports_domain.common.MainCoroutineRule
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineRule::class)
internal class CountryLeaguesUseCaseImplTest {

    private val sportsRepository: SportsRepository = mockk()
    private lateinit var countryLeaguesUseCaseImpl: CountryLeaguesUseCaseImpl

    @BeforeEach
    fun setUp() {
        countryLeaguesUseCaseImpl = CountryLeaguesUseCaseImpl(sportsRepository = sportsRepository)
    }

    @Test
    fun `GIVEN query country name WHEN use case invoked THEN verify repository called`() = runTest {
        val countryName = "India"
        coEvery { sportsRepository.searchLeaguesByCountry(countryName = countryName) } returns flow {
            ApiResult.Success(
                mockk<LeagueListModel>()
            )
        }


        countryLeaguesUseCaseImpl.invoke(params = countryName)

        coVerify {
            sportsRepository.searchLeaguesByCountry(countryName = countryName)
        }
    }
}