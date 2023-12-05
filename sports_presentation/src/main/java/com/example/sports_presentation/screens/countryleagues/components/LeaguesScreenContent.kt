package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesViewIntent
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesViewModel
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesViewState
/**
 * This composable represents the screen content, which collects the view state of the view model to load the appropriate composable based on the view state.
 */
@Composable
fun LeaguesScreenContent(countryName: String) {
    val viewModel: CountryLeaguesViewModel = hiltViewModel()
    val leaguesViewState = viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.sendIntent(CountryLeaguesViewIntent.LoadData(countryName = countryName))
    }

    when (leaguesViewState.value) {
        is CountryLeaguesViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryLeaguesViewState.Success -> {
            val leaguesList =
                (leaguesViewState.value as CountryLeaguesViewState.Success).leaguesList
            LeaguesContainer(leaguesList = leaguesList)


        }
        is CountryLeaguesViewState.Error -> {
            val errorMessage =
                (leaguesViewState.value as CountryLeaguesViewState.Error).errorMessage
            MessageScreen(message = errorMessage)
        }
        is CountryLeaguesViewState.NoDataFound -> {
            MessageScreen(message = stringResource(id = R.string.no_leagues_found))
        }
    }
}