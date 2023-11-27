package com.example.sports_data.mappers.countryleagues

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_data.dto.countryleagues.LeagueResponseDTO
import javax.inject.Inject

class LeaguesListMapper  @Inject constructor(private val leaguesMapper: LeaguesMapper) {

    fun map(input: LeagueResponseDTO): LeagueListModel {
        return with(input) {
            val leaguesList = countries?.map { leaguesMapper.map(it) }
            LeagueListModel(countries = leaguesList?: listOf())
        }
    }
}