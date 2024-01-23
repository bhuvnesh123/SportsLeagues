package com.example.sports_data.service

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.CoroutineDispatcher

interface SportsService {
    suspend fun getAllCountries(dispatcher: CoroutineDispatcher): ApiResult<CountriesListModel>

    suspend fun searchLeaguesByCountry(
        dispatcher: CoroutineDispatcher,
        countryName: String,
    ): ApiResult<LeagueListModel>
}
