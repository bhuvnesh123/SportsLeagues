package com.example.sports_presentation.countieslist

import com.example.sports_domain.usecase.CountryListUseCase
import com.example.sports_presentation.base.BaseViewModel
import javax.inject.Inject

class CountryListViewModel @Inject constructor(countryListUseCase: CountryListUseCase) :
    BaseViewModel<CountryListViewState, CountryListViewIntent, CountryListSideEffect>() {

    override fun createInitialState(): CountryListViewState {
        TODO("Not yet implemented")
    }

    override fun sendIntent(vi: CountryListViewIntent) {
        when (vi) {
            CountryListViewIntent.LoadData -> {
                TODO("Not yet implemented")
            }
        }
    }
}