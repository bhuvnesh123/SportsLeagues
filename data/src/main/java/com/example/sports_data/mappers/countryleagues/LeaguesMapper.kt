package com.example.sports_data.mappers.countryleagues

import com.example.sports_data.dto.countryleagues.LeagueDTO
import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import javax.inject.Inject

class LeaguesMapper @Inject constructor() {

    fun map(input: LeagueDTO): LeagueModel = with(input) {
        LeagueModel(
            strSport = strSport.orEmpty(),
            strLeague = strLeague.orEmpty(),
            strCurrentSeason = strCurrentSeason.orEmpty(),
            intFormedYear = intFormedYear.orEmpty(),
            strDescriptionEN = strDescriptionEN.orEmpty(),
            strTvRights = strTvRights.orEmpty(),
        )
    }
}
