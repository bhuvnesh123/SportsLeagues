package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.wrapper.ApiResult
import com.example.sports_domain.repository.SportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryListUseCaseImpl @Inject constructor(private val sportsRepository: SportsRepository) :
    UseCase<Unit, CountriesListModel> {

    override operator fun invoke(params: Unit): Flow<ApiResult<CountriesListModel>> =
        sportsRepository.getAllCountries()
}
