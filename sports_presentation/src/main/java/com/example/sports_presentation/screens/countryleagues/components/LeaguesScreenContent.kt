package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.UIText
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesContract
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesViewModel

/**
 * This composable represents the screen content, which collects the view state of the view model to load the appropriate composable based on the view state.
 */
@Composable
fun LeaguesScreenContent(countryName: String) {
    val viewModel: CountryLeaguesViewModel = hiltViewModel()
    val leaguesViewState = viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.sendIntent(CountryLeaguesContract.ViewIntent.LoadData(countryName = countryName))
    }

    when (leaguesViewState.value) {
        is CountryLeaguesContract.ViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryLeaguesContract.ViewState.Success -> {
            val leaguesList =
                (leaguesViewState.value as CountryLeaguesContract.ViewState.Success).leaguesList
            LeaguesContainer(leaguesList = leaguesList)


        }
        is CountryLeaguesContract.ViewState.Error -> {
            val errorMessage =
                (leaguesViewState.value as CountryLeaguesContract.ViewState.Error).errorMessage
            MessageScreen(
                message = UIText.getText(
                    uiText = errorMessage,
                    context = LocalContext.current
                )
            )
        }
        is CountryLeaguesContract.ViewState.NoDataFound -> {
            MessageScreen(message = stringResource(id = R.string.no_leagues_found))
        }
    }
}