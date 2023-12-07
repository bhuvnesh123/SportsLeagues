package com.example.sports_presentation.screens.countryleagues

import com.example.common.UIText
import com.example.sports_presentation.base.SideEffect
import com.example.sports_presentation.base.ViewIntent
import com.example.sports_presentation.base.ViewState
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel

sealed interface CountryLeaguesViewState : ViewState {
    object Loading :
        CountryLeaguesViewState     // An object is a singleton instance of a class, and in this case, it represents the loading state of the view. Since loading is a single state and doesn't require any additional data, it can be represented as an object.

    data class Success(val leaguesList: List<LeaguesPresentationModel>) :
        CountryLeaguesViewState  // This class represent the success state of the view. It can hold additional data such as the list of countries in the case of Success .


    data class Error(val errorMessage: UIText) : CountryLeaguesViewState

    object NoDataFound : CountryLeaguesViewState
}

sealed interface CountryLeaguesViewIntent : ViewIntent {
    class LoadData(val countryName: String) : CountryLeaguesViewIntent
}

sealed interface CountryLeaguesSideEffect : SideEffect