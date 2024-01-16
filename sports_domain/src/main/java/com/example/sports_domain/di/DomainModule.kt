package com.example.sports_domain.di

import com.example.sports_domain.usecase.CountryLeaguesUseCase
import com.example.sports_domain.usecase.CountryLeaguesUseCaseImpl
import com.example.sports_domain.usecase.CountryListUseCase
import com.example.sports_domain.usecase.CountryListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun bindCountryLeaguesUseCase(countryLeaguesUseCaseImpl: CountryLeaguesUseCaseImpl): CountryLeaguesUseCase

    @Binds
    abstract fun bindCountryListUseCase(countryListUseCaseImpl: CountryListUseCaseImpl): CountryListUseCase
}
