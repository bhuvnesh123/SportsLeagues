package com.example.sports_presentation.screens.countryleagues

import androidx.lifecycle.viewModelScope
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.usecase.CountryLeaguesUseCase
import com.example.sports_presentation.base.BaseViewModel
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryLeaguesViewModel @Inject constructor(
    private val countryLeaguesUseCase: CountryLeaguesUseCase,
    private val leaguesListPresentationMapper: LeaguesListPresentationMapper
) : BaseViewModel<CountryLeaguesViewState, CountryLeaguesViewIntent, CountryLeaguesSideEffect>() {

    override fun createInitialState(): CountryLeaguesViewState = CountryLeaguesViewState.Loading


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
        _state.value =
            if (mappedResponse.countries.isEmpty()) CountryLeaguesViewState.NoDataFound else CountryLeaguesViewState.Success(
                mappedResponse.countries
            )
    }

    override fun sendIntent(vi: CountryLeaguesViewIntent) {
        when (vi) {
            is CountryLeaguesViewIntent.LoadData -> getCountryLeagues(countryName = vi.countryName)
        }
    }
}