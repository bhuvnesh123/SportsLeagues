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

@Composable
fun LeaguesContainer(leaguesList: List<LeaguesPresentationModel>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = Dimens.grid_0_5)
            .fillMaxWidth()
            .padding(vertical = Dimens.grid_0_5),
    ) {
        items(leaguesList) { item ->
            LeaguesListItem(league = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeaguesContainerPreview(){

    val leaguesList = listOf(
        LeaguesPresentationModel(
            "Premier League",
            "Football",
            "The Premier League is the top level of the English football league system.",
            "1992",
            "2021-2022",
            "Sky Sports, BT Sport"
        ),
        LeaguesPresentationModel(
            "NBA",
            "Basketball",
            "The National Basketball Association is a men's professional basketball league in North America.",
            "1946",
            "2021-2022",
            "ABC, ESPN, TNT"
        ),
        LeaguesPresentationModel(
            "MLB",
            "Baseball",
            "Major League Baseball is a professional baseball organization and the oldest of the major professional sports leagues in the United States and Canada.",
            "1903",
            "2021",
            "ESPN, Fox, TBS"
        )
    )
    LeaguesContainer(leaguesList = leaguesList)
}