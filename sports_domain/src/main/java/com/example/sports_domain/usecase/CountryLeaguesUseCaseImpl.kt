package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryLeaguesUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) : CountryLeaguesUseCase {

    override operator fun invoke(countryName: String): Flow<ApiResult<LeagueListModel?>> {
        return sportsRepository.searchLeaguesByCountry(countryName)
    }
}