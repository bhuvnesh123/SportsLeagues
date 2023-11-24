package com.example.sports_presentation.screens.countieslist

import androidx.lifecycle.viewModelScope
import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.usecase.CountryListUseCase
import com.example.sports_presentation.base.BaseViewModel
import com.example.sports_presentation.mappers.CountryPresentationListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryListUseCase: CountryListUseCase,
    private val countryPresentationListMapper: CountryPresentationListMapper
) :
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
        val mappedResponse = countryPresentationListMapper.map(response)
        _state.value = CountryListViewState.Success(mappedResponse.countries)
    }

    private fun navigateToDetails(countryName: String) {
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