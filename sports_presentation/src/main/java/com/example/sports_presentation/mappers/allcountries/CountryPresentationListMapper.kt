package com.example.sports_presentation.mappers.allcountries

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_presentation.models.allcountries.CountriesListPresentationModel
import javax.inject.Inject

class CountryPresentationListMapper @Inject constructor(private val countryPresentationMapper: CountryPresentationMapper) {
    fun map(input: CountriesListModel): CountriesListPresentationModel = with(input) {
        val countriesList = countries.map { countryPresentationMapper.map(it) }
        CountriesListPresentationModel(countries = countriesList)
    }
}
