package com.example.sports_data.mappers

import com.example.sports_domain.domainModels.allCountries.CountriesListModel
import com.example.sports_data.dto.allCountries.CountriesResponse

interface CountriesListMapper {
    fun map(input: CountriesResponse): CountriesListModel
}