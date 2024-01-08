package com.example.sports_presentation.screens.countryleagues.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.UIText
import com.example.sports_presentation.R
import com.example.sports_presentation.customcomposables.CircularProgressBarIndicator
import com.example.sports_presentation.customcomposables.MessageScreen
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel
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

@Preview(showBackground = true)
@Composable
fun LeaguesLoadingStatePreview() {
    LeaguesViewState(
        viewState = CountryLeaguesContract.ViewState.Loading,
    )
}

@Preview(showBackground = true)
@Composable
fun LeaguesSuccessStatePreview() {
    val leaguesList = listOf(
        LeaguesPresentationModel(
            leagueName = "Premier League",
            sport = "Football",
            leagueDescription = "The Premier League is the top level of the English football league system.",
            formedYear = "1992",
            currentSeason = "2021-2022",
            tvRights = "Sky Sports, BT Sport",
        ),
        LeaguesPresentationModel(
            leagueName = "NBA",
            sport = "Basketball",
            leagueDescription = "The National Basketball Association is a men's professional basketball league in North America.",
            formedYear = "1946",
            currentSeason = "2021-2022",
            tvRights = "ABC, ESPN, TNT",
        ),
        LeaguesPresentationModel(
            leagueName = "MLB",
            sport = "Baseball",
            leagueDescription = "Major League Baseball is a professional baseball organization and the oldest of the major professional sports leagues in the United States and Canada.",
            formedYear = "1903",
            currentSeason = "2021",
            tvRights = "ESPN, Fox, TBS",
        ),
    )
    LeaguesViewState(
        viewState = CountryLeaguesContract.ViewState.Success(
            leaguesList = leaguesList,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun LeaguesErrorStatePreview() {
    LeaguesViewState(
        viewState = CountryLeaguesContract.ViewState.Error(
            errorMessage = UIText.DynamicString(
                "No leagues found for this country",
            ),
        ),
    )
}
