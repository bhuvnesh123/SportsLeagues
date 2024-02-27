package com.example.sports_presentation.screens.countieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UIText
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.CountryListUseCase
import com.example.sports_presentation.base.MVIContract
import com.example.sports_presentation.base.MVIDelegate
import com.example.sports_presentation.di.IODispatcher
import com.example.sports_presentation.di.MainDispatcher
import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryListUseCase: CountryListUseCase,
    private val countryPresentationListMapper: CountryPresentationListMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) :
    ViewModel(),
    MVIContract<CountryListContract.ViewState, CountryListContract.ViewIntent, CountryListContract.SideEffect> by MVIDelegate(
        initialViewState = CountryListContract.ViewState.Loading,
    ) {

    init {
        sendIntent(viewIntent = CountryListContract.ViewIntent.LoadData)
    }

    private fun getCountryList() {
        viewModelScope.launch(context = ioDispatcher) {
            when (val apiResult = countryListUseCase()) {
                is ApiResult.Success -> onSuccess(response = apiResult.value)
                is ApiResult.ErrorResponse -> onFailure(
                    message = UIText.DynamicString(
                        apiResult.errorModel.message,
                    ),
                )
                is ApiResult.GenericError -> apiResult.errorMessage?.let { onFailure(message = it) }
                is ApiResult.NetworkError -> onFailure(message = apiResult.errorMessage)
            }
        }
    }

    private suspend fun onFailure(message: UIText) {
        withContext(mainDispatcher) {
            updateViewState(viewState = CountryListContract.ViewState.Error(errorMessage = message))
        }
    }

    private suspend fun onSuccess(response: CountriesListModel) {
        val mappedResponse = countryPresentationListMapper.map(input = response)
        withContext(mainDispatcher) {
            updateViewState(viewState = CountryListContract.ViewState.Success(countriesList = mappedResponse.countries))
        }
    }

    private fun navigateToDetails(countryName: String) {
        viewModelScope.launch {
            emitSideEffect(sideEffect = CountryListContract.SideEffect.NavigateToDetails(countryName = countryName))
        }
    }

    override fun sendIntent(viewIntent: CountryListContract.ViewIntent) {
        when (viewIntent) {
            is CountryListContract.ViewIntent.LoadData -> getCountryList()
            is CountryListContract.ViewIntent.OnCountryClicked -> navigateToDetails(viewIntent.countryName)
        }
    }
}
