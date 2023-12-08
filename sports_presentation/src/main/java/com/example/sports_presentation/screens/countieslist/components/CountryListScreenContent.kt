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
        viewModel.sendIntent(vi = CountryListContract.ViewIntent.LoadData)
        viewModel.sideEffect.collect {
            if (it is CountryListContract.SideEffect.NavigateToDetails) {
                val countryName = it.countryName
                callback(countryName)
            }
        }
    }

    when (countryListViewState.value) {
        is CountryListContract.ViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryListContract.ViewState.Success -> {
            val countriesList =
                (countryListViewState.value as CountryListContract.ViewState.Success).countriesList
            CountriesListContainer(
                countriesList = countriesList,
                callback = { countryName ->
                    viewModel.sendIntent(
                        CountryListContract.ViewIntent.OnCountryClicked(
                            countryName = countryName
                        )
                    )
                }
            )

        }
        is CountryListContract.ViewState.Error -> {
            val errorMessage =
                (countryListViewState.value as CountryListContract.ViewState.Error).errorMessage
            MessageScreen(
                message = UIText.getText(
                    uiText = errorMessage,
                    context = LocalContext.current
                )
            )
        }
    }
}





