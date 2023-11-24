package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.FailureScreen
import com.example.sports_presentation.models.allCountries.CountryPresentationModel
import com.example.sports_presentation.screens.countieslist.CountryListViewIntent
import com.example.sports_presentation.screens.countieslist.CountryListViewModel
import com.example.sports_presentation.screens.countieslist.CountryListViewState

@Composable
fun CountryListScreen(){
    val viewModel : CountryListViewModel = hiltViewModel()
    val countryListViewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.sendIntent(CountryListViewIntent.LoadData)
    }

    when(countryListViewState.value){
        is CountryListViewState.Loading->{
            CircularProgressBarIndicator()
        }
        is CountryListViewState.Success ->{
            val countriesList  = (countryListViewState.value as CountryListViewState.Success).countriesList
            CountriesList(countriesList)

        }
        is CountryListViewState.Error -> {
            val errorMessage = (countryListViewState.value as CountryListViewState.Error).errorMessage
            FailureScreen(errorMessage = errorMessage)
        }
    }
}

@Composable
fun CountriesList(countriesName : List<CountryPresentationModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(countriesName) { item ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.name_en)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    val countries = listOf(
        CountryPresentationModel("United States"),
        CountryPresentationModel("Canada"),
        CountryPresentationModel("Mexico"),
        CountryPresentationModel("Brazil"),
        CountryPresentationModel("Argentina"),
        CountryPresentationModel("United Kingdom"),
        CountryPresentationModel("France"),
        CountryPresentationModel("Germany"),
        CountryPresentationModel("Italy"),
        CountryPresentationModel("Spain"),
        CountryPresentationModel("Russia"),
        CountryPresentationModel("China"),
        CountryPresentationModel("Japan"),
        CountryPresentationModel("India"),
        CountryPresentationModel("Australia")
    )
    CountriesList(countries)
}
