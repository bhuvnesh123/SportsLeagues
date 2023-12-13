package com.example.sports_presentation.mappers.allcountries

import com.example.sports_domain.domainmodels.allcountries.CountryModel
import com.example.sports_presentation.models.allcountries.CountryPresentationModel

class CountryPresentationMapper {

    fun map(input: CountryModel): CountryPresentationModel = with(input) {
        CountryPresentationModel(countryName = name_en)
    }
}
