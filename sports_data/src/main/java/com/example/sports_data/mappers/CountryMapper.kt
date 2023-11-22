package com.example.sports_data.mappers

import com.example.sports_data.dto.allcountries.CountryDTO
import com.example.sports_domain.domainmodels.allcountries.CountryModel

class CountryMapper {
    fun map(input: CountryDTO): CountryModel {
        return with(input){
            CountryModel(name_en)
        }
    }
}