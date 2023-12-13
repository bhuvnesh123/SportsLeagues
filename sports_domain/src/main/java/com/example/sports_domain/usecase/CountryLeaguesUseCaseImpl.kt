package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryLeaguesUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) :
    UseCase<String, LeagueListModel> {

    override operator fun invoke(params: String): Flow<ApiResult<LeagueListModel>> =
        sportsRepository.searchLeaguesByCountry(countryName = params)
}
