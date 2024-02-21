package com.example.sports_presentation.screens.countryleagues

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UIText
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.CountryLeaguesUseCase
import com.example.sports_presentation.base.MVIContract
import com.example.sports_presentation.base.MVIDelegate
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import com.example.sports_presentation.navigation.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryLeaguesViewModel @Inject constructor(
    private val countryLeaguesUseCase: CountryLeaguesUseCase,
    private val leaguesListPresentationMapper: LeaguesListPresentationMapper,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), MVIContract<CountryLeaguesContract.ViewState, CountryLeaguesContract.ViewIntent, CountryLeaguesContract.SideEffect> by MVIDelegate(initialViewState = CountryLeaguesContract.ViewState.Loading) {

    init {
        val countryName = checkNotNull(savedStateHandle.get<String>(NavigationScreens.COUNTRY_NAME_ARG))
        sendIntent(viewIntent = CountryLeaguesContract.ViewIntent.LoadData(countryName = countryName))
    }

    private fun getCountryLeagues(countryName: String) {
        viewModelScope.launch {
            when (val apiResult = countryLeaguesUseCase(countryName = countryName)) {
                is ApiResult.Success -> onSuccess(response = apiResult.value)
                is ApiResult.GenericError -> apiResult.errorMessage?.let { onFailure(message = it) }
                is ApiResult.NetworkError -> onFailure(message = apiResult.errorMessage)
            }
        }
    }

    private fun onFailure(message: UIText) {
        updateViewState(viewState = CountryLeaguesContract.ViewState.Error(errorMessage = message))
    }

    private fun onSuccess(response: LeagueListModel) {
        val mappedResponse = leaguesListPresentationMapper.map(input = response)
        updateViewState(
            viewState = if (mappedResponse.countries.isEmpty()) {
                CountryLeaguesContract.ViewState.NoDataFound
            } else {
                CountryLeaguesContract.ViewState.Success(
                    leaguesList = mappedResponse.countries,
                )
            },
        )
    }

    override fun sendIntent(viewIntent: CountryLeaguesContract.ViewIntent) {
        when (viewIntent) {
            is CountryLeaguesContract.ViewIntent.LoadData -> getCountryLeagues(countryName = viewIntent.countryName)
        }
    }
}
