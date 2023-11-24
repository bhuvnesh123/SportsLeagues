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
import androidx.compose.ui.tooling.preview.Preview
import com.example.sports_presentation.common.component.Dimens

@Composable
fun CountriesListItem(countryName: String, onClick: (countryName: String) -> Unit) {

    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
        modifier = Modifier
            .padding(vertical = Dimens.grid_0_5, horizontal = Dimens.grid_1)
            .clickable { onClick(countryName) }
    ) {
        Row(
            modifier = Modifier
                .padding(Dimens.grid_1_5)
                .fillMaxWidth()
        ) {
            Text(
                text = countryName,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = Dimens.text_medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListItemPreview() {
    CountriesListItem(countryName = "India", onClick = {})
}
