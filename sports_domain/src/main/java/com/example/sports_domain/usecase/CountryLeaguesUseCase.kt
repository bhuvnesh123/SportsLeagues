package com.example.sports_domain.usecase

import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import kotlinx.coroutines.flow.Flow

interface CountryLeaguesUseCase {
    operator fun invoke(countryName: String): Flow<ApiResult<LeagueListModel?>>
}