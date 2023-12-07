package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sports_presentation.common.component.Dimens
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel

/**
 * This composable represents the list container, which consists of a [LazyColumn] to hold the list.
 */
@Composable
fun LeaguesContainer(leaguesList: List<LeaguesPresentationModel>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = Dimens.grid_0_5)
            .fillMaxWidth()
            .padding(vertical = Dimens.grid_0_5),
    ) {
        items(items = leaguesList) { item ->
            LeaguesListItem(league = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeaguesContainerPreview() {

    val leaguesList = listOf(
        LeaguesPresentationModel(
            leagueName = "Premier League",
            sport = "Football",
            leagueDescription = "The Premier League is the top level of the English football league system.",
            formedYear = "1992",
            currentSeason = "2021-2022",
            tvRights = "Sky Sports, BT Sport"
        ),
        LeaguesPresentationModel(
            leagueName = "NBA",
            sport = "Basketball",
            leagueDescription = "The National Basketball Association is a men's professional basketball league in North America.",
            formedYear = "1946",
            currentSeason = "2021-2022",
            tvRights = "ABC, ESPN, TNT"
        ),
        LeaguesPresentationModel(
            leagueName = "MLB",
            sport = "Baseball",
            leagueDescription = "Major League Baseball is a professional baseball organization and the oldest of the major professional sports leagues in the United States and Canada.",
            formedYear = "1903",
            currentSeason = "2021",
            tvRights = "ESPN, Fox, TBS"
        )
    )
    LeaguesContainer(leaguesList = leaguesList)
}