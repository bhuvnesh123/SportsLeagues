package com.example.sports_presentation.mappers

import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_presentation.models.allCountries.CountryPresentationModel

class CountryPresentationMapper {

    fun map(input: CountryModel): CountryPresentationModel {
        return with(input) {
            CountryPresentationModel(name_en)
        }
    }
}