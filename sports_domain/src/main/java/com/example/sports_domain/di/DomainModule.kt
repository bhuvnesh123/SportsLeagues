package com.example.sports_domain.di

import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.usecase.CountryLeaguesUseCaseImpl
import com.example.sports_domain.usecase.CountryListUseCaseImpl
import com.example.sports_domain.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun bindCountryLeaguesUseCase(countryLeaguesUseCaseImpl: CountryLeaguesUseCaseImpl): UseCase<String, LeagueListModel>

    @Binds
    abstract fun bindCountryListUseCase(countryListUseCaseImpl: CountryListUseCaseImpl): UseCase<Unit, CountriesListModel>
}