package com.example.sports_presentation.screens.countieslist

import com.example.sports_presentation.base.SideEffect
import com.example.sports_presentation.base.ViewIntent
import com.example.sports_presentation.base.ViewState
import com.example.sports_presentation.models.allcountries.CountryPresentationModel

sealed interface CountryListViewState : ViewState {
    object Loading :
        CountryListViewState     // An object is a singleton instance of a class, and in this case, it represents the loading state of the view. Since loading is a single state and doesn't require any additional data, it can be represented as an object.

    data class Success(val countriesList: List<CountryPresentationModel>) :
        CountryListViewState // This class represent the success state of the view. It can hold additional data such as the list of countries in the case of Success .


    data class Error(val errorMessage: String) : CountryListViewState
}

sealed interface CountryListViewIntent : ViewIntent {
    object LoadData : CountryListViewIntent
    class OnCountryClicked(val countryName: String) : CountryListViewIntent
}

sealed interface CountryListSideEffect : SideEffect {
    class NavigateToDetails(val countryName: String) : CountryListSideEffect
}