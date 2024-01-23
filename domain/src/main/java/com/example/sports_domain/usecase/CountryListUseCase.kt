package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult

interface CountryListUseCase {
    suspend operator fun invoke(): ApiResult<CountriesListModel>
}
