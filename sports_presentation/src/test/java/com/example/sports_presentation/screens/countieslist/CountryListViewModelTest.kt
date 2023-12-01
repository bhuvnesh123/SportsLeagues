package com.example.sports_presentation.screens.countieslist

import com.example.sports_presentation.common.MainCoroutineRule
import com.example.sports_presentation.mappers.allcountries.CountryPresentationListMapper
import com.example.sports_presentation.mappers.allcountries.CountryPresentationMapper
import com.example.sports_presentation.models.allcountries.CountryPresentationModel
import com.example.sports_presentation.screens.countieslist.fakes.FakeCountryListUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineRule::class)
internal class CountryListViewModelTest {

    private lateinit var countryListViewModel: CountryListViewModel


    @BeforeEach
    fun setUp() {
        countryListViewModel = CountryListViewModel(
            countryListUseCase = FakeCountryListUseCase(),
            countryPresentationListMapper = CountryPresentationListMapper(
                CountryPresentationMapper()
            )
        )

    }


    @Test
    fun `GIVE Country list data WHEN LoadData ViewIntent sent THEN viewState contains list of countries`() =
        runTest {
            countryListViewModel.sendIntent(CountryListViewIntent.LoadData)

            assertEquals(
                countryListViewModel.viewState.value, CountryListViewState.Success(
                    listOf(
                        CountryPresentationModel("United States"),
                        CountryPresentationModel("Canada"),
                        CountryPresentationModel("Mexico")
                    )
                )
            )
        }
}