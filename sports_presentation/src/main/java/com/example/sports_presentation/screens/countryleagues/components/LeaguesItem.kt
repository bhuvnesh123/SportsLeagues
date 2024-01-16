package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.sports_presentation.R
import com.example.sports_presentation.common.component.Dimens
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel

/**
 * This composable represents an item of the list.
 */
@Composable
fun LeaguesListItem(league: LeaguesPresentationModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.dimen_32),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.plane_4,
        ),
    ) {
        Column(
            modifier = Modifier.padding(
                all = Dimens.dimen_16,
            ),
        ) {
            Text(
                text = league.leagueName,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(
                modifier = Modifier.height(
                    height = Dimens.dimen_8,
                ),
            )
            Text(
                text = stringResource(id = R.string.sport).format(league.sport),
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(
                modifier = Modifier.height(
                    height = Dimens.dimen_8,
                ),
            )
            Text(
                text = stringResource(id = R.string.formed_year).format(league.formedYear),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier = Modifier.height(
                    height = Dimens.dimen_8,
                ),
            )
            Text(
                text = stringResource(id = R.string.current_season).format(league.currentSeason),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier = Modifier.height(
                    height = Dimens.dimen_8,
                ),
            )
            if (league.tvRights.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.tv_rights).format(league.tvRights),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            if (league.leagueDescription.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(
                        height = Dimens.dimen_8,
                    ),
                )
                Text(
                    text = league.leagueDescription,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
