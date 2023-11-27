package com.example.sports_presentation.screens.countryleagues

import androidx.lifecycle.viewModelScope
import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.usecase.CountryLeaguesUseCase
import com.example.sports_presentation.base.BaseViewModel
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryLeaguesViewModel @Inject constructor(
    private val countryLeaguesUseCase: CountryLeaguesUseCase,
    private val leaguesListPresentationMapper: LeaguesListPresentationMapper
) : BaseViewModel<CountryLeaguesViewState, CountryLeaguesViewIntent, CountryLeaguesSideEffect>() {

    override fun createInitialState(): CountryLeaguesViewState {
        return CountryLeaguesViewState.Loading
    }

    private fun getCountryLeagues(countryName: String) {
        viewModelScope.launch {
            countryLeaguesUseCase(countryName = countryName).collect { result ->
                when (result) {
                    is ApiResult.Success -> result.value?.let { onResponse(response = it) }
                    is ApiResult.GenericError -> result.errorMessage?.let { onFailure(message = it) }
                    is ApiResult.NetworkError -> onFailure(result.errorMessage)
                }
            }
        }
    }

    private fun onFailure(message: String) {
        _state.value = CountryLeaguesViewState.Error(errorMessage = message)
    }

    private fun onResponse(response: LeagueListModel) {
        val mappedResponse = leaguesListPresentationMapper.map(response)
        _state.value = CountryLeaguesViewState.Success(mappedResponse.countries)
    }

    override fun sendIntent(vi: CountryLeaguesViewIntent) {
        when (vi) {
            is CountryLeaguesViewIntent.LoadData -> getCountryLeagues(countryName = vi.countryName)
        }
    }
}