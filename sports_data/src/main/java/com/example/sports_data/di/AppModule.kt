package com.example.sports_data.di

import com.example.sports_data.api.SportsApi
import com.example.sports_data.mappers.allcountries.CountriesListMapper
import com.example.sports_data.mappers.allcountries.CountryMapper
import com.example.sports_data.mappers.countryleagues.LeaguesListMapper
import com.example.sports_data.mappers.countryleagues.LeaguesMapper
import com.example.sports_data.repository.SportsRepositoryImpl
import com.example.sports_data.service.SportsService
import com.example.sports_data.service.SportsServiceImpl
import com.example.sports_domain.domainmodels.allcountries.CountriesListModel
import com.example.sports_domain.domainmodels.countryleagues.LeagueListModel
import com.example.sports_domain.repository.SportsRepository
import com.example.sports_domain.usecase.CountryLeaguesUseCaseImpl
import com.example.sports_domain.usecase.CountryListUseCaseImpl
import com.example.sports_domain.usecase.UseCase
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
    fun provideCountriesMapper(): CountryMapper = CountryMapper()


    @Singleton
    @Provides
    fun provideCountriesListMapper(countryMapper: CountryMapper): CountriesListMapper =
        CountriesListMapper(countryMapper = countryMapper)


    @Singleton
    @Provides
    fun provideLeaguesMapper(): LeaguesMapper = LeaguesMapper()

    @Singleton
    @Provides
    fun provideLeaguesListMapper(leaguesMapper: LeaguesMapper): LeaguesListMapper =
        LeaguesListMapper(leaguesMapper = leaguesMapper)


    @Singleton
    @Provides
    fun provideSportsService(
        sportsApi: SportsApi,
        countriesListMapper: CountriesListMapper,
        leaguesListMapper: LeaguesListMapper
    ): SportsService = SportsServiceImpl(
        sportsApi = sportsApi,
        countriesListMapper = countriesListMapper,
        leaguesListMapper = leaguesListMapper
    )


    @Singleton
    @Provides
    fun providesSportsRepository(sportsService: SportsService): SportsRepository =
        SportsRepositoryImpl(sportsService = sportsService)


    @Singleton
    @Provides
    fun providesCountryLeaguesUseCase(sportsRepository: SportsRepository): UseCase<String, LeagueListModel> =
        CountryLeaguesUseCaseImpl(sportsRepository = sportsRepository)


    @Singleton
    @Provides
    fun providesCountryListUseCase(sportsRepository: SportsRepository): UseCase<Unit,CountriesListModel> =
        CountryListUseCaseImpl(sportsRepository = sportsRepository)


}