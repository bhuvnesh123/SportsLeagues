package com.example.sports_data.mappers

import com.example.sports_data.dto.allCountries.Country
import com.example.sports_domain.domainModels.allCountries.CountryModel

class CountryMapperImpl : CountryMapper {
    override fun map(input: Country): CountryModel {
        return with(input){
            CountryModel(name_en)
        }
    }
}