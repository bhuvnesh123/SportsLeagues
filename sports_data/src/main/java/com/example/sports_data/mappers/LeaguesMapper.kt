package com.example.sports_data.mappers

import com.example.sports_data.dto.countryLeagues.League
import com.example.sports_domain.domainModels.countryLeagues.LeagueModel

interface LeaguesMapper {
    fun map(input: League): LeagueModel
}