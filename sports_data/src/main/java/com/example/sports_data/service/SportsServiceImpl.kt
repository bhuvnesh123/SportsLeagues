package com.example.sports_data.service

import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.utils.safeApiCall
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
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
            val response = safeApiCall({ sportsApi.getAllCountries() }, countriesListMapper::map)
            emit(response)
        }


    override fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>> =
        flow {
            val response = safeApiCall(
                { sportsApi.searchLeaguesByCountry(countryName = countryName) },
                leaguesListMapper::map
            )
            emit(response)
        }

}