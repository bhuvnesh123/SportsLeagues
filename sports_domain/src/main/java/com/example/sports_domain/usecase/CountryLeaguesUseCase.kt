package com.example.sports_domain.usecase
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult

interface CountryLeaguesUseCase {
    suspend operator fun invoke(countryName: String): ApiResult<LeagueListModel>
}