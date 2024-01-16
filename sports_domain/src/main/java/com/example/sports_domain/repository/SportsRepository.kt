package com.example.sports_domain.repository

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult

interface SportsRepository {
    suspend fun getAllCountries(): ApiResult<CountriesListModel>
    suspend fun searchLeaguesByCountry(countryName: String): ApiResult<LeagueListModel>
}
