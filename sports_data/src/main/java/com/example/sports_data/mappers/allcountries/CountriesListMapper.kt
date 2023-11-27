package com.example.sports_data.mappers.allcountries

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_data.dto.allcountries.CountriesResponseDTO
import javax.inject.Inject

class CountriesListMapper @Inject constructor(private val countryMapper: CountryMapper){

    fun map(input: CountriesResponseDTO): CountriesListModel {
        return with(input) {
            val countriesList = countries.map { countryMapper.map(it) }
            CountriesListModel(countriesList)
        }

    }
}