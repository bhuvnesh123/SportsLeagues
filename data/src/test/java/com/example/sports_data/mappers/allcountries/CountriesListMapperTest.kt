package com.example.sports_data.mappers.allcountries

import com.example.sports_data.mappers.fakes.ResponseDTOFake
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CountriesListMapperTest {
    private lateinit var countriesListMapper: CountriesListMapper

    @BeforeEach
    fun setUp() {
        countriesListMapper = CountriesListMapper(countryMapper = CountryMapper())
    }

    @Test
    fun `GIVEN list of countries dto as input WHEN mapper method called THEN return converted domain model list`() {
        val input = ResponseDTOFake.getCountryResponseDTO()
        val expectedOutput = CountriesListModel(
            countries = listOf(
                CountryModel(name = "Country 1"),
                CountryModel(name = "Country 2"),
                CountryModel(name = "Country 3"),
            ),
        )

        val result = countriesListMapper.map(input = input)

        assertEquals(
            expectedOutput,
            result,
        )
    }
}
