package com.example.sports_presentation.mappers.countryleagues

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel
import javax.inject.Inject

class LeaguesListPresentationMapper @Inject constructor() {
    fun map(input: LeagueListModel): List<LeaguesPresentationModel> =
        input.countries.map {
            LeaguesPresentationModel(
                leagueName = it.strLeague,
                sport = it.strSport,
                leagueDescription = it.strDescriptionEN,
                formedYear = it.intFormedYear,
                currentSeason = it.strCurrentSeason,
                tvRights = it.strTvRights,
            )
        }
}
