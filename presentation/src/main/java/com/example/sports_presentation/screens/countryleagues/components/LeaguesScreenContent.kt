package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesContract
import com.example.sports_presentation.screens.countryleagues.CountryLeaguesViewModel

/**
 * This composable represents the screen content, which collects the view state of the view model to load the appropriate composable based on the view state.
 */
@Composable
fun LeaguesScreenContent() {
    val viewModel: CountryLeaguesViewModel = hiltViewModel()
    val leaguesViewState by viewModel.viewState.collectAsStateWithLifecycle()
    LeaguesViewState(viewState = leaguesViewState)
}

@Composable
fun LeaguesViewState(viewState: CountryLeaguesContract.ViewState) {
    when (viewState) {
        is CountryLeaguesContract.ViewState.Loading -> {
            CircularProgressBarIndicator()
        }
        is CountryLeaguesContract.ViewState.Success -> {
            val leaguesList = viewState.leaguesList
            LeaguesContainer(leaguesList = leaguesList)
        }
        is CountryLeaguesContract.ViewState.Error -> {
            val errorMessage = viewState.errorMessage
            MessageScreen(
                message = errorMessage,
            )
        }
        is CountryLeaguesContract.ViewState.NoDataFound -> {
            MessageScreen(
                message = stringResource(
                    id = R.string.no_leagues_found,
                ),
            )
        }
    }
}
