package com.example.sports_domain

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import kotlinx.coroutines.flow.Flow

interface SportsRepository {
    fun getAllCountries(): Flow<ApiResult<CountriesListModel?>>
    fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>>
}