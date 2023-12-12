package com.example.sports_data.di

import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.allcountries.CountryMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesMapper
import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.repository.SportsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {
    companion object {

        @Provides
        fun provideCountriesMapper(): CountryMapper = CountryMapper()


        @Provides
        fun provideCountriesListMapper(countryMapper: CountryMapper): CountriesListMapper =
            CountriesListMapper(countryMapper = countryMapper)


        @Provides
        fun provideLeaguesMapper(): LeaguesMapper = LeaguesMapper()

        @Provides
        fun provideLeaguesListMapper(leaguesMapper: LeaguesMapper): LeaguesListMapper =
            LeaguesListMapper(leaguesMapper = leaguesMapper)

    }

    @Binds
    abstract fun bindSportsService(sportsServiceImpl: SportsServiceImpl): SportsService

    @Binds
    abstract fun bindSportsRepository(sportsRepositoryImpl: SportsRepositoryImpl): SportsRepository

}