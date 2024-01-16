package com.example.sports_presentation.screens.countieslist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.UIText
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.models.allcountries.CountryPresentationModel
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

@Preview(showBackground = true)
@Composable
fun CountryListLoadingStatePreview() {
    CountryListViewState(
        viewState = CountryListContract.ViewState.Loading,
        callback = {},
    )
}

@Preview(showBackground = true)
@Composable
fun CountryListSuccessStatePreview() {
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
        CountryPresentationModel(countryName = "Australia"),
    )
    CountryListViewState(
        viewState = CountryListContract.ViewState.Success(countriesList = countries),
        callback = {},
    )
}

@Preview(showBackground = true)
@Composable
fun CountryListErrorStatePreview() {
    CountryListViewState(
        viewState = CountryListContract.ViewState.Error(errorMessage = UIText.DynamicString("Apologies, there seems to be a problem with the network connection. Please ensure you are connected to the internet and try again")),
        callback = {},
    )
}
