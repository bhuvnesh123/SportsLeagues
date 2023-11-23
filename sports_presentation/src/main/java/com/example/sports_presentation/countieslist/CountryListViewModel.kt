package com.example.sports_presentation.countieslist

import androidx.lifecycle.viewModelScope
import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.usecase.CountryListUseCase
import com.example.sports_presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(private val countryListUseCase: CountryListUseCase) :
    BaseViewModel<CountryListViewState, CountryListViewIntent, CountryListSideEffect>() {

    override fun createInitialState(): CountryListViewState {
        return CountryListViewState.Loading
    }

    private fun getCountryList() {
        viewModelScope.launch {
            countryListUseCase().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.value?.let { response ->
                            onResponse(response = response)
                        }
                    }
                    is ApiResult.GenericError -> result.errorMessage?.let { onFailure(message = it) }
                    is ApiResult.NetworkError -> onFailure(result.errorMessage)
                }
            }
        }
    }

    private fun onFailure(message: String) {
        _state.value = CountryListViewState.Error(errorMessage = message)
    }

    private fun onResponse(response: CountriesListModel) {
        _state.value = CountryListViewState.Success(response.countries)
    }

    private fun navigateToDetails(countryName : String) {
        viewModelScope.launch {
            _sideEffect.send(CountryListSideEffect.NavigateToDetails(countryName = countryName))
        }
    }

    override fun sendIntent(vi: CountryListViewIntent) {
        when (vi) {
            is CountryListViewIntent.LoadData -> {
                getCountryList()
            }
            is CountryListViewIntent.OnCountryClicked -> {
                navigateToDetails(vi.countryName)
            }
        }
    }
}