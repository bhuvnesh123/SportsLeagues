package com.example.sports_data.service

import com.example.sports_data.api.SportsApi
import com.example.common.ApiResult
import com.example.common.map
import com.example.common.safeApiCall
import com.example.sports_domain.domainModels.allCountries.CountriesListModel
import com.example.sports_domain.domainModels.countryLeagues.LeagueListModel
import com.example.sports_data.mappers.CountriesListMapper
import com.example.sports_data.mappers.LeaguesListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportsServiceImpl @Inject constructor(private val sportsApi: SportsApi, private val countriesListMapperImpl: CountriesListMapper, private val leaguesListMapperImpl: LeaguesListMapper) : SportsService {

    override fun getAllCountries(): Flow<ApiResult<CountriesListModel?>> =
        flow {
            val response = safeApiCall { sportsApi.getAllCountries() }
            emit(response.map { countriesResponse -> countriesResponse?.let { countriesListMapperImpl.map(it) }})
        }


    override fun searchLeaguesByCountry(countryName: String): Flow<ApiResult<LeagueListModel?>> =
        flow {
            val response = safeApiCall { sportsApi.searchLeaguesByCountry(countryName = countryName) }
            response.map { leaguesResponse -> leaguesResponse?.let {leaguesListMapperImpl.map(leaguesResponse) }}
        }

}