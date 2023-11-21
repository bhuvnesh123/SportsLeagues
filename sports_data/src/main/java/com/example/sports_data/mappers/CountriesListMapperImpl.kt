package com.example.sports_data.mappers

import com.example.sports_domain.domainModels.allCountries.CountriesListModel
import com.example.sports_data.dto.allCountries.CountriesResponse
import javax.inject.Inject

class CountriesListMapperImpl @Inject constructor(private val countryMapper: CountryMapper) : CountriesListMapper{

    override fun map(input: CountriesResponse): CountriesListModel {
        return with(input) {
            val countriesList = countries.map { countryMapper.map(it) }
            CountriesListModel(countriesList)
        }

    }
}