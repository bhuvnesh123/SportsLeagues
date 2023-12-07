package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.screens.countieslist.CountryListViewIntent
import com.example.sports_presentation.screens.countieslist.CountryListViewModel
import com.example.sports_presentation.screens.countieslist.CountryListViewState

/**
 * This composable represents the screen content, which collects the view state of the view model to load the appropriate composable based on the view state.
 */
@Composable
fun CountryListScreenContent(callback: (countryName: String) -> Unit) {
    val viewModel: CountryListViewModel = hiltViewModel()
    val countryListViewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(vi = CountryListViewIntent.LoadData)
    }

    when (countryListViewState.value) {
        is CountryListViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryListViewState.Success -> {
            val countriesList =
                (countryListViewState.value as CountryListViewState.Success).countriesList
            CountriesListContainer(
                countriesList = countriesList,
                callback = callback
            )

        }
        is CountryListViewState.Error -> {
            val errorMessage =
                (countryListViewState.value as CountryListViewState.Error).errorMessage
            MessageScreen(message = errorMessage)
        }
    }
}





