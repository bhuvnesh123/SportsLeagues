package com.example.sports_data.repository

import com.example.sports_data.service.SportsService
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val sportsService: SportsService,
) :
    SportsRepository {
    override suspend fun getAllCountries(): ApiResult<CountriesListModel> =
        sportsService.getAllCountries()

    override suspend fun searchLeaguesByCountry(countryName: String): ApiResult<LeagueListModel> =
        sportsService.searchLeaguesByCountry(
            countryName = countryName,
        )
}
