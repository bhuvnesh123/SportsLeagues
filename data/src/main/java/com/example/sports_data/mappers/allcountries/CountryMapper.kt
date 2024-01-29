package com.example.sports_data.mappers.allcountries

import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_domain.domainmodels.allcountries.CountryModel
import javax.inject.Inject

class CountryMapper @Inject constructor() {
    fun map(input: CountryDTO): CountryModel = with(input) {
        CountryModel(name = name)
    }
}
