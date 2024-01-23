package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import javax.inject.Inject

class CountryLeaguesUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) : CountryLeaguesUseCase {

    override suspend operator fun invoke(countryName: String): ApiResult<LeagueListModel> =
        sportsRepository.searchLeaguesByCountry(countryName = countryName)
}
