package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sports_presentation.common.component.Dimens

/**
 * This composable represents an item of the list.
 */
@Composable
fun CountriesListItem(countryName: String, onClick: (countryName: String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(
                vertical = Dimens.dimen_4,
                horizontal = Dimens.diment_8,
            )
            .clickable { onClick(countryName) },
    ) {
        Row(
            modifier = Modifier
                .padding(Dimens.dimen_12)
                .fillMaxWidth(),
        ) {
            Text(
                text = countryName,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = Dimens.text_medium,
            )
        }
    }
}
