package com.example.sports_presentation.mappers.countryleagues

import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_presentation.models.countryleagues.LeagueListPresentationModel
import javax.inject.Inject

class LeaguesListPresentationMapper @Inject constructor(private val leaguesPresentationMapper: LeaguesPresentationMapper) {
    fun map(input: LeagueListModel): LeagueListPresentationModel = with(input) {
        val leaguesList = countries.map { leaguesPresentationMapper.map(it) }
        LeagueListPresentationModel(leaguesList)
    }

}