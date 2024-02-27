package com.example.sports_presentation.screens.countieslist

import com.example.sports_presentation.base.MVIContract
import com.example.sports_presentation.models.allcountries.CountryPresentationModel

interface CountryListContract :
    MVIContract<CountryListContract.ViewState, CountryListContract.ViewIntent, CountryListContract.SideEffect> {
    sealed interface ViewState {
        data object Loading :
            ViewState // An object is a singleton instance of a class, and in this case, it represents the loading state of the view. Since loading is a single state and doesn't require any additional data, it can be represented as an object.

        data class Success(val countriesList: List<CountryPresentationModel>) :
            ViewState // This class represent the success state of the view. It can hold additional data such as the list of countries in the case of Success .

        data class Error(val errorMessage: String) : ViewState
    }

    sealed interface ViewIntent {
        data object LoadData : ViewIntent
        data class OnCountryClicked(val countryName: String) : ViewIntent
    }

    sealed interface SideEffect {
        data class NavigateToDetails(val countryName: String) : SideEffect
    }
}
