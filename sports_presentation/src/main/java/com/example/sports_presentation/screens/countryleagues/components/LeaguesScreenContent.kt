package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var initialApiCalled by rememberSaveable { mutableStateOf(false) }
    if (initialApiCalled.not()) {
        LaunchedEffect(Unit) {
            viewModel.sendIntent(CountryLeaguesContract.ViewIntent.LoadData(countryName = countryName))
            initialApiCalled = true
        }
    }
    LeaguesViewState(viewState = leaguesViewState.value)
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
                message = UIText.getText(
                    uiText = errorMessage,
                    context = LocalContext.current,
                ),
            )
        }
        is CountryLeaguesContract.ViewState.NoDataFound -> {
            MessageScreen(message = stringResource(id = R.string.no_leagues_found))
        }
    }
}
