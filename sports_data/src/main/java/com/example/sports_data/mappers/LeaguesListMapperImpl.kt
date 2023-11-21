package com.example.sports_data.mappers

import com.example.sports_domain.domainModels.countryLeagues.LeagueListModel
import com.example.sports_data.dto.countryLeagues.LeagueResponse
import javax.inject.Inject

class LeaguesListMapperImpl  @Inject constructor(private val leaguesMapper: LeaguesMapper) : LeaguesListMapper {

    override fun map(input: LeagueResponse): LeagueListModel {
        return with(input) {
            val leaguesList = countries.map { leaguesMapper.map(it) }
            LeagueListModel(countries = leaguesList)
        }
    }
}