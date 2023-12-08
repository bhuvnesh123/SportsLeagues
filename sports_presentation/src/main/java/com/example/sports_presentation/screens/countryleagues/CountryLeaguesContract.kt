package com.example.sports_presentation.screens.countryleagues

import com.example.common.UIText
import com.example.sports_presentation.base.MVIContract
import com.example.sports_presentation.models.countryleagues.LeaguesPresentationModel

interface CountryLeaguesContract :
    MVIContract<CountryLeaguesContract.ViewState, CountryLeaguesContract.ViewIntent, CountryLeaguesContract.SideEffect> {
    sealed interface ViewState {
        object Loading :
            ViewState     // An object is a singleton instance of a class, and in this case, it represents the loading state of the view. Since loading is a single state and doesn't require any additional data, it can be represented as an object.

        data class Success(val leaguesList: List<LeaguesPresentationModel>) :
            ViewState  // This class represent the success state of the view. It can hold additional data such as the list of countries in the case of Success .


        data class Error(val errorMessage: UIText) : ViewState

        object NoDataFound : ViewState
    }

    sealed interface ViewIntent {
        class LoadData(val countryName: String) : ViewIntent
    }

    sealed interface SideEffect
}