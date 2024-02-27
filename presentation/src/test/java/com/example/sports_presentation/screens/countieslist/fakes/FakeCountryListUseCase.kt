package com.example.sports_presentation.screens.countieslist.fakes

import com.example.common.UIText
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.CountryListUseCase
import kotlinx.coroutines.delay

class FakeCountryListUseCase : CountryListUseCase {

    private var apiError: ApiResult<CountriesListModel>? = null

    override suspend operator fun invoke(): ApiResult<CountriesListModel> {
        delay(1000) // Simulate a delay in the API response for 1 second
        return apiError ?: run {
            val countriesList = listOf(
                CountryModel(name = "United States"),
                CountryModel(name = "Canada"),
                CountryModel(name = "Mexico"),
            )
            // Create a fake CountriesListModel object
            val countries = CountriesListModel(countries = countriesList)
            // Create a fake ApiResult object with the CountriesListModel
            ApiResult.Success(value = countries)
        }
    }

    fun setShouldEmitError(
        apiResult: ApiResult<CountriesListModel> = ApiResult.GenericError(
            code = 400,
            errorMessage = UIText.DynamicString(
                input = BAD_REQUEST,
            ),
        ),
    ) {
        apiError = apiResult
    }

    private companion object {
        const val BAD_REQUEST = "Bad Request"
    }
}
