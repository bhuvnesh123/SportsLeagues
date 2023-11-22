package com.example.sports_data.service

import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import kotlinx.coroutines.flow.Flow

interface SportsService {
    fun getAllCountries(): Flow<ApiResult<CountriesListModel?>>
    fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>>
}