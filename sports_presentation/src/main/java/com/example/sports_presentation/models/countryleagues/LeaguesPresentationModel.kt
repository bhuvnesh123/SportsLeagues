package com.example.sports_presentation.models.countryleagues

data class LeaguesPresentationModel(
    val leagueName: String,
    val sport: String,
    val leagueDescription: String,
    val formedYear: String,
    val currentSeason: String,
    val tvRights: String
)