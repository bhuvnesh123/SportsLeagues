package com.example.sports_data.repository

import com.example.common.ApiResult
import com.example.sports_domain.domainModels.allCountries.CountriesListModel
import com.example.sports_domain.domainModels.countryLeagues.LeagueListModel
import com.example.sports_data.service.SportsService
import com.example.sports_domain.SportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(private val sportsService : SportsService) : SportsRepository {
    override fun getAllCountries(): Flow<ApiResult<CountriesListModel?>> {
        return sportsService.getAllCountries()
    }

    override fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>> {
        return sportsService.searchLeaguesByCountry(countryName=countryName)
    }
}