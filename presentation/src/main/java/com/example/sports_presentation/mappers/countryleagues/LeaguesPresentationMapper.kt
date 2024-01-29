package com.example.sports_presentation.mappers.countryleagues

import com.example.sports_domain.domainmodels.countryleagues.LeagueModel
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel
import javax.inject.Inject

class LeaguesPresentationMapper @Inject constructor() {

    fun map(input: LeagueModel): LeaguesPresentationModel = with(input) {
        LeaguesPresentationModel(
            leagueName = strLeague,
            sport = strSport,
            leagueDescription = strDescriptionEN,
            formedYear = intFormedYear,
            currentSeason = strCurrentSeason,
            tvRights = strTvRights,
        )
    }
}
