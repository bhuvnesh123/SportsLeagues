package com.example.sports_presentation.countieslist

import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_presentation.base.SideEffect
import com.example.sports_presentation.base.ViewIntent
import com.example.sports_presentation.base.ViewState

sealed interface CountryListViewState : ViewState {
    object Loading : CountryListViewState
    class Success(countriesList: List<CountryModel>) : CountryListViewState
    class Error(throwable: Throwable) : CountryListViewState
}

sealed interface CountryListViewIntent : ViewIntent {
    object LoadData : CountryListViewIntent
}

sealed interface CountryListSideEffect : SideEffect {
    class NavigateToDetails(countryName: String)
}