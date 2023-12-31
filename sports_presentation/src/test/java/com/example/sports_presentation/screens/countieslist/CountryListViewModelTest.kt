package com.example.sports_presentation.screens.countieslist

import com.example.common.UIText
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_presentation.common.MainCoroutineRule
import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import com.example.sports_presentation.mappers.allcountries.CountryPresentationMapper
import com.example.sports_presentation.models.allcountries.CountryPresentationModel
import com.example.sports_presentation.screens.countieslist.fakes.FakeCountryListUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MainCoroutineRule::class)
internal class CountryListViewModelTest {

    private lateinit var countryListViewModel: CountryListViewModel
    private val fakeCountryListUseCase = FakeCountryListUseCase()

    @BeforeEach
    fun setUp() {
        countryListViewModel = CountryListViewModel(
            countryListUseCase = fakeCountryListUseCase,
            countryPresentationListMapper = CountryPresentationListMapper(
                countryPresentationMapper = CountryPresentationMapper(),
            ),
        )
    }

    @Test
    fun `GIVEN Country list data WHEN LoadData ViewIntent sent THEN viewState contains list of countries`() =
        runTest {
            fakeCountryListUseCase.setShouldEmitError(isError = false)
            countryListViewModel.sendIntent(CountryListContract.ViewIntent.LoadData)

            assertEquals(
                countryListViewModel.viewState.value,
                CountryListContract.ViewState.Success(
                    countriesList = listOf(
                        CountryPresentationModel(countryName = "United States"),
                        CountryPresentationModel(countryName = "Canada"),
                        CountryPresentationModel(countryName = "Mexico"),
                    ),
                ),
            )
        }

    @ParameterizedTest(name = "GIVEN api error response {0} data WHEN LoadData ViewIntent sent THEN viewState contains expected error {1} message")
    @MethodSource("countryListErrorResult")
    fun `GIVEN api error response data WHEN LoadData ViewIntent sent THEN viewState contains expected error message`(
        apiResult: ApiResult<CountriesListModel>,
        message: String,
    ) =
        runTest {
            fakeCountryListUseCase.setShouldEmitError(
                isError = true,
                apiResult = apiResult,
            )
            countryListViewModel.sendIntent(CountryListContract.ViewIntent.LoadData)

            assertEquals(
                countryListViewModel.viewState.value,
                CountryListContract.ViewState.Error(
                    errorMessage = UIText.DynamicString(
                        input = message,
                    ),
                ),
            )
        }

    @Test
    fun `GIVEN Country name clicked WHEN OnCountryClicked ViewIntent sent THEN sideEffect contains NavigateToDetails(countryName)`() =
        runTest {
            val countryName = "India"
            countryListViewModel.sendIntent(CountryListContract.ViewIntent.OnCountryClicked(countryName = countryName))

            assertEquals(
                countryListViewModel.sideEffect.first(),
                CountryListContract.SideEffect.NavigateToDetails(countryName),
            )
        }

    private companion object {
        const val CHECK_INTERNET_CONNECTION = "Please check your internet connection"
        const val BAD_REQUEST = "Bad Request"
        const val BAD_REQUEST_CODE = 400

        @JvmStatic
        fun countryListErrorResult() = listOf(
            arrayOf(
                ApiResult.GenericError(
                    code = BAD_REQUEST_CODE,
                    errorMessage = UIText.DynamicString(
                        input = BAD_REQUEST,
                    ),
                ),
                BAD_REQUEST,
            ),
            arrayOf(
                ApiResult.NetworkError(
                    UIText.DynamicString(input = CHECK_INTERNET_CONNECTION),
                ),
                CHECK_INTERNET_CONNECTION,
            ),

        )
    }
}
