package com.example.sports_domain.usecase

import com.example.sports_domain.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import kotlinx.coroutines.flow.Flow

interface CountryListUseCase {
    operator fun invoke(): Flow<ApiResult<CountriesListModel?>>
}