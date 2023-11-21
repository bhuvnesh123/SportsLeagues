package com.example.sports_domain

import com.example.common.ApiResult
import com.example.sports_domain.domainModels.allCountries.CountriesListModel
import com.example.sports_domain.domainModels.countryLeagues.LeagueListModel
import kotlinx.coroutines.flow.Flow

interface SportsRepository {
    fun getAllCountries(): Flow<ApiResult<CountriesListModel?>>
    fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>>
}