package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryListUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) : CountryListUseCase {

    override operator fun invoke(): Flow<ApiResult<CountriesListModel?>> {
        return sportsRepository.getAllCountries()
    }
}