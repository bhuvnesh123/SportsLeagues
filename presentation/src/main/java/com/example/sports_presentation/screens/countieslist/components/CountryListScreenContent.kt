package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.screens.countieslist.CountryListContract
import com.example.sports_presentation.screens.countieslist.CountryListViewModel

/**
 * This composable represents the screen content, which collects the view state of the view model to load the appropriate composable based on the view state.
 */
@Composable
fun CountryListScreenContent(callback: (countryName: String) -> Unit) {
    val viewModel: CountryListViewModel = hiltViewModel()
    val countryListViewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            if (it is CountryListContract.SideEffect.NavigateToDetails) {
                val countryName = it.countryName
                callback(countryName)
            }
        }
    }
    CountryListViewState(viewState = countryListViewState, callback = { countryName ->
        viewModel.sendIntent(
            CountryListContract.ViewIntent.OnCountryClicked(
                countryName = countryName,
            ),
        )
    })
}

@Composable
fun CountryListViewState(
    viewState: CountryListContract.ViewState,
    callback: (countryName: String) -> Unit,
) {
    when (viewState) {
        is CountryListContract.ViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryListContract.ViewState.Success -> {
            val countriesList = viewState.countriesList
            CountriesListContainer(
                countriesList = countriesList,
                callback = callback,
            )
        }
        is CountryListContract.ViewState.Error -> {
            val errorMessage = viewState.errorMessage
            MessageScreen(
                message = errorMessage,
            )
        }
    }
}
