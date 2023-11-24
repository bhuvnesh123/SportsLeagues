package com.example.sports_presentation.mappers

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_presentation.models.allCountries.CountriesListPresentationModel
import javax.inject.Inject

class CountryPresentationListMapper @Inject constructor(private val countryPresentationMapper: CountryPresentationMapper) {
    fun map(input: CountriesListModel): CountriesListPresentationModel {
        return with(input) {
            val countriesList = countries.map { countryPresentationMapper.map(it) }
            CountriesListPresentationModel(countriesList)
        }

    }
}