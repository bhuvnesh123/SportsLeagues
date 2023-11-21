package com.example.sports_data.mappers

import com.example.sports_domain.domainModels.countryLeagues.LeagueListModel
import com.example.sports_data.dto.countryLeagues.LeagueResponse

interface LeaguesListMapper {
    fun map(input: LeagueResponse): LeagueListModel
}