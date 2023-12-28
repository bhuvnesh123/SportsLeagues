package com.example.sports_presentation.screens.countieslist.fakes

import com.example.common.UIText
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCountryListUseCase : UseCase<Unit, CountriesListModel> {

    private var apiError: ApiResult<CountriesListModel>? = null

    override fun invoke(params: Unit): Flow<ApiResult<CountriesListModel?>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {
            val countriesList = listOf(
                CountryModel(name = "United States"),
                CountryModel(name = "Canada"),
                CountryModel(name = "Mexico"),
            )
            // Create a fake CountriesListModel object
            val countries = CountriesListModel(countries = countriesList)

            // Create a fake ApiResult object with the CountriesListModel
            val apiResult = ApiResult.Success(value = countries)

            // Create a fake Flow object that emits the fake ApiResult
            flow {
                emit(value = apiResult)
            }
        }
    }

    fun setShouldEmitError(
        isError: Boolean,
        apiResult: ApiResult<CountriesListModel> = ApiResult.GenericError(
            code = 400,
            errorMessage = UIText.DynamicString(
                input = BAD_REQUEST,
            ),
        ),
    ) {
        apiError = if (isError) apiResult else null
    }

    private companion object {
        const val BAD_REQUEST = "Bad Request"
    }
}
