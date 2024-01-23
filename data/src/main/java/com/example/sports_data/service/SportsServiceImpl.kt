package com.example.sports_data.service

import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.utils.safeApiCall
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SportsServiceImpl @Inject constructor(
    private val sportsApi: SportsApi,
    private val countriesListMapper: CountriesListMapper,
    private val leaguesListMapper: LeaguesListMapper,
) : SportsService {

    override suspend fun getAllCountries(dispatcher: CoroutineDispatcher): ApiResult<CountriesListModel> =
        safeApiCall(
            apiCall = { sportsApi.getAllCountries() },
            mapper = countriesListMapper::map,
            dispatcher = dispatcher,
        )

    override suspend fun searchLeaguesByCountry(
        dispatcher: CoroutineDispatcher,
        countryName: String,
    ): ApiResult<LeagueListModel> =
        safeApiCall(
            apiCall = {
                sportsApi.searchLeaguesByCountry(
                    countryName = countryName,
                )
            },
            mapper = leaguesListMapper::map,
            dispatcher = dispatcher,
        )
}
