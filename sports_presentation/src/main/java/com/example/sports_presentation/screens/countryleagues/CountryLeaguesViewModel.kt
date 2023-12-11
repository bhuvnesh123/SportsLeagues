package com.example.sports_presentation.screens.countryleagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UIText
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.UseCase
import com.example.sports_presentation.mappers.countryleagues.LeaguesListPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryLeaguesViewModel @Inject constructor(
    private val countryLeaguesUseCase: UseCase<String, LeagueListModel>,
    private val leaguesListPresentationMapper: LeaguesListPresentationMapper
) : ViewModel(), CountryLeaguesContract {


    override fun createInitialState(): CountryLeaguesContract.ViewState =
        CountryLeaguesContract.ViewState.Loading

    private val _state = MutableStateFlow(value = createInitialState())
    private val _sideEffect = Channel<CountryLeaguesContract.SideEffect>()

    override val viewState: StateFlow<CountryLeaguesContract.ViewState>
        get() = _state.asStateFlow()

    override val sideEffect: Flow<CountryLeaguesContract.SideEffect>
        get() = _sideEffect.consumeAsFlow()

    private fun getCountryLeagues(countryName: String) {
        viewModelScope.launch {
            countryLeaguesUseCase(params = countryName).collect { result ->
                when (result) {
                    is ApiResult.Success -> result.value?.let { onResponse(response = it) }
                    is ApiResult.GenericError -> result.errorMessage?.let { onFailure(message = it) }
                    is ApiResult.NetworkError -> onFailure(message = result.errorMessage)
                }
            }
        }
    }

    private fun onFailure(message: UIText) {
        _state.value = CountryLeaguesContract.ViewState.Error(errorMessage = message)
    }

    private fun onResponse(response: LeagueListModel) {
        val mappedResponse = leaguesListPresentationMapper.map(input = response)
        _state.value =
            if (mappedResponse.countries.isEmpty()) CountryLeaguesContract.ViewState.NoDataFound else CountryLeaguesContract.ViewState.Success(
                leaguesList = mappedResponse.countries
            )
    }

    override fun sendIntent(vi: CountryLeaguesContract.ViewIntent) {
        if (vi is CountryLeaguesContract.ViewIntent.LoadData) {
            getCountryLeagues(countryName = vi.countryName)
        }
    }
}