package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.UIText
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
    val countryListViewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            if (it is CountryListContract.SideEffect.NavigateToDetails) {
                val countryName = it.countryName
                callback(countryName)
            }
        }
    }
    CountryListViewState(viewState = countryListViewState.value, callback = { countryName ->
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
                message = UIText.getText(
                    uiText = errorMessage,
                    context = LocalContext.current,
                ),
            )
        }
    }
}
