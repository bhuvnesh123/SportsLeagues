package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sports_presentation.common.component.Dimens
import com.example.sports_presentation.models.allcountries.CountryPresentationModel

/**
 * This composable represents the list container, which consists of a [LazyColumn] to hold the list.
 */
@Composable
fun CountriesListContainer(
    countriesList: List<CountryPresentationModel>,
    callback: (countryName: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = Dimens.dimen_4)
            .fillMaxWidth()
            .padding(vertical = Dimens.dimen_4),
    ) {
        items(items = countriesList) { item ->
            CountriesListItem(
                countryName = item.countryName,
                onClick = callback,
            )
        }
    }
}
