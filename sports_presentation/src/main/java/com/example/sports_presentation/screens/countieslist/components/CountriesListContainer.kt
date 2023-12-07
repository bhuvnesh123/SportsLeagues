package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sports_presentation.common.component.Dimens
import com.example.sports_presentation.models.allcountries.CountryPresentationModel

/**
 * This composable represents the list container, which consists of a [LazyColumn] to hold the list.
 */
@Composable
fun CountriesListContainer(
    countriesList: List<CountryPresentationModel>,
    callback: (countryName: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = Dimens.grid_0_5)
            .fillMaxWidth()
            .padding(vertical = Dimens.grid_0_5),
    ) {
        items(items = countriesList) { item ->
            CountriesListItem(
                countryName = item.countryName,
                onClick = callback
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountyListPreview() {
    val countries = listOf(
        CountryPresentationModel(countryName = "United States"),
        CountryPresentationModel(countryName = "Canada"),
        CountryPresentationModel(countryName = "Mexico"),
        CountryPresentationModel(countryName = "Brazil"),
        CountryPresentationModel(countryName = "Argentina"),
        CountryPresentationModel(countryName = "United Kingdom"),
        CountryPresentationModel(countryName = "France"),
        CountryPresentationModel(countryName = "Germany"),
        CountryPresentationModel(countryName = "Italy"),
        CountryPresentationModel(countryName = "Spain"),
        CountryPresentationModel(countryName = "Russia"),
        CountryPresentationModel(countryName = "China"),
        CountryPresentationModel(countryName = "Japan"),
        CountryPresentationModel(countryName = "India"),
        CountryPresentationModel(countryName = "Australia")
    )
    CountriesListContainer(countriesList = countries,
        callback = {}
    )
}
