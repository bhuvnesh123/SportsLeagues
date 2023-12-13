package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
