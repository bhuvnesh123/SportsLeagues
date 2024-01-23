package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import javax.inject.Inject

class CountryListUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) : CountryListUseCase {

    override suspend operator fun invoke(): ApiResult<CountriesListModel> =
        sportsRepository.getAllCountries()
}
