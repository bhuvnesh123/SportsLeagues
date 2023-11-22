package com.example.sports_data.di

import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.CountriesListMapper
import com.example.sports_data.mappers.CountryMapper
import com.example.sports_data.mappers.LeaguesListMapper
import com.example.sports_data.mappers.LeaguesMapper
import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.SportsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCountriesMapper(): CountryMapper {
        return CountryMapper()
    }

    @Singleton
    @Provides
    fun provideCountriesListMapper(countryMapper: CountryMapper): CountriesListMapper {
        return CountriesListMapper(countryMapper = countryMapper)
    }

    @Singleton
    @Provides
    fun provideLeaguesMapper(): LeaguesMapper {
        return LeaguesMapper()
    }

    @Singleton
    @Provides
    fun provideLeaguesListMapper(leaguesMapper: LeaguesMapper): LeaguesListMapper {
        return LeaguesListMapper(leaguesMapper = leaguesMapper)
    }

    @Singleton
    @Provides
    fun provideSportsService(
        sportsApi: SportsApi,
        countriesListMapper: CountriesListMapper,
        leaguesListMapper: LeaguesListMapper
    ): SportsService {
        return SportsServiceImpl(
            sportsApi = sportsApi,
            countriesListMapper = countriesListMapper,
            leaguesListMapper = leaguesListMapper
        )
    }

    @Singleton
    @Provides
    fun providesSportsRepository(sportsService: SportsService): SportsRepository {
        return SportsRepositoryImpl(sportsService = sportsService)
    }


}