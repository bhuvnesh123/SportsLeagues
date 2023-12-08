package com.example.sports_presentation.screens.countieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UIText
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.UseCase
import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryListUseCase: UseCase<Unit, CountriesListModel>,
    private val countryPresentationListMapper: CountryPresentationListMapper
) :
    ViewModel(), CountryListContract {

    override fun createInitialState(): CountryListContract.ViewState =
        CountryListContract.ViewState.Loading

    private val _state = MutableStateFlow(value = createInitialState())
    private val _sideEffect = Channel<CountryListContract.SideEffect>()

    override val viewState: StateFlow<CountryListContract.ViewState>
        get() = _state.asStateFlow()

    override val sideEffect: Flow<CountryListContract.SideEffect>
        get() = _sideEffect.receiveAsFlow()


    private fun getCountryList() {
        viewModelScope.launch {
            countryListUseCase(params = Unit).collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.value?.let { response ->
                            onResponse(response = response)
                        }
                    }
                    is ApiResult.GenericError -> result.errorMessage?.let { onFailure(message = it) }
                    is ApiResult.NetworkError -> onFailure(message = result.errorMessage)
                }
            }
        }
    }

    private fun onFailure(message: UIText) {
        _state.value = CountryListContract.ViewState.Error(errorMessage = message)
    }

    private fun onResponse(response: CountriesListModel) {
        val mappedResponse = countryPresentationListMapper.map(input = response)
        _state.value =
            CountryListContract.ViewState.Success(countriesList = mappedResponse.countries)
    }

    private fun navigateToDetails(countryName: String) {
        viewModelScope.launch {
            _sideEffect.send(CountryListContract.SideEffect.NavigateToDetails(countryName = countryName))
        }
    }

    override fun sendIntent(vi: CountryListContract.ViewIntent) = when (vi) {
        is CountryListContract.ViewIntent.LoadData -> {
            getCountryList()
        }
        is CountryListContract.ViewIntent.OnCountryClicked -> {
            navigateToDetails(vi.countryName)
        }
    }

}