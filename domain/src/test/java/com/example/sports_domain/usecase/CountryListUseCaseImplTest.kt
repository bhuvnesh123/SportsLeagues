package com.example.sports_domain.usecase

import com.example.sports_domain.common.MainCoroutineRule
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineRule::class)
internal class CountryListUseCaseImplTest {
    private val sportsRepository: SportsRepository = mockk()
    private lateinit var countryListUseCaseImpl: CountryListUseCaseImpl

    @BeforeEach
    fun setUp() {
        countryListUseCaseImpl = CountryListUseCaseImpl(sportsRepository = sportsRepository)
    }

    @Test
    fun `GIVEN query list of countries WHEN use case invoked THEN verify repository called`() = runTest {
        coEvery { sportsRepository.getAllCountries() } returns
            ApiResult.Success(value = mockk())

        countryListUseCaseImpl()

        coVerify {
            sportsRepository.getAllCountries()
        }
    }
}
