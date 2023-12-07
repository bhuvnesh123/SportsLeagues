package com.example.sports_data.service

import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.utils.safeApiCall
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportsServiceImpl @Inject constructor(
    private val sportsApi: SportsApi,
    private val countriesListMapper: CountriesListMapper,
    private val leaguesListMapper: LeaguesListMapper
) : SportsService {

    override fun getAllCountries(): Flow<ApiResult<CountriesListModel?>> =
        flow {
            emit(
                safeApiCall(
                    apiCall = { sportsApi.getAllCountries() },
                    mapper = countriesListMapper::map
                )
            )
        }


    override fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>> =
        flow {
            emit(
                safeApiCall(
                    apiCall = { sportsApi.searchLeaguesByCountry(countryName = countryName) },
                    mapper = leaguesListMapper::map
                )
            )
        }

}