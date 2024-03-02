package com.example.sports_presentation.mappers.allcountries

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_presentation.models.allcountries.CountryPresentationModel
import javax.inject.Inject

class CountryPresentationListMapper @Inject constructor() {
    fun map(input: CountriesListModel) = input.countries.map { CountryPresentationModel(countryName = it.name) }
}
