package com.example.sports_data.mappers

import com.example.sports_data.dto.allCountries.Country
import com.example.sports_domain.domainModels.allCountries.CountryModel


interface CountryMapper {
    fun map(input: Country): CountryModel
}