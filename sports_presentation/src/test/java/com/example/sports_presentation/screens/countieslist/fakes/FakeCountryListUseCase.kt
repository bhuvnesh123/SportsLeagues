package com.example.sports_presentation.screens.countieslist.fakes

import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_domain.usecase.CountryListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCountryListUseCase : CountryListUseCase {
    override fun invoke(): Flow<ApiResult<CountriesListModel?>> {
        val countriesList = listOf(
            CountryModel(name_en = "United States"),
            CountryModel(name_en = "Canada"),
            CountryModel(name_en = "Mexico")
        )
        // Create a fake CountriesListModel object
        val countries = CountriesListModel(countries = countriesList)

        // Create a fake ApiResult object with the CountriesListModel
        val apiResult = ApiResult.Success(value = countries)

        // Create a fake Flow object that emits the fake ApiResult
        return flow {
            emit(value = apiResult)
        }
    }
}
